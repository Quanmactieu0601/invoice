package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.Company;
import study.invoice.domain.InvoiceTemplate;
import study.invoice.domain.User;
import study.invoice.dto.InvTemplateDTO;
import study.invoice.enm.RoleName;
import study.invoice.repository.CompanyRepository;
import study.invoice.repository.InvoiceTemplateRepository;
import study.invoice.repository.TemplateViewRepository;
import study.invoice.service.InvoiceTemplateService;
import study.invoice.service.Mapping.MappingHelper;

import java.util.List;

@Service
public class InvoiceTemplateServiceImpl implements InvoiceTemplateService {
    @Autowired
    InvoiceTemplateRepository invoiceTemplateRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    TemplateViewRepository templateViewRepository;

    @Override
    public List<InvTemplateDTO> getAll(User user) {
        List<InvoiceTemplate> datas;
        if(user.getAuthorities().contains(RoleName.ROOT)){
            datas = invoiceTemplateRepository.findAll();
        }
        else{
            datas = invoiceTemplateRepository.getByComID(user.getComID());
            if(user.getAuthorities().contains(RoleName.SUPER_ADMIN)){
                List<Company> companies = companyRepository.getByParentComID(user.getComID());
                for (Company item : companies) {
                    datas.addAll(invoiceTemplateRepository.getByComID(item.getId()));
                }
            }
        }
        return MappingHelper.map(datas, InvTemplateDTO.class);
    }

    @Override
    public InvoiceTemplate createNew(InvTemplateDTO invTemplateDTO, User user) {
        if(!templateViewRepository.existsById(invTemplateDTO.getTemplateViewID())){
            throw new RuntimeException("Không tồn tại mẫu hóa đơn trong hệ thống");
        }
        if(invoiceTemplateRepository.existsByComIDAndPattern(user.getComID(), invTemplateDTO.getPattern())){
            throw new RuntimeException("Công ty đã đăng ký mẫu số " + invTemplateDTO.getPattern());
        }

        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        invoiceTemplate.setComID(user.getComID());
        invoiceTemplate.setPattern(invoiceTemplate.getPattern());
        invoiceTemplate.setTemplateViewID(invTemplateDTO.getTemplateViewID());
        invoiceTemplateRepository.save(invoiceTemplate);
        return invoiceTemplate;
    }


}
