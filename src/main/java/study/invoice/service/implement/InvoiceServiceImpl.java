package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.Invoice;
import study.invoice.domain.Product;
import study.invoice.domain.ProductInv;
import study.invoice.domain.User;
import study.invoice.dto.InvoiceDTO;
import study.invoice.dto.ProductInvDTO;
import study.invoice.enm.InvoiceStatus;
import study.invoice.repository.InvoiceRepository;
import study.invoice.repository.ProductInvRepository;
import study.invoice.repository.ProductRepository;
import study.invoice.service.InvoiceService;
import study.invoice.service.Mapping.MappingHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductInvRepository productInvRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<InvoiceDTO> getAllByComIDAndPattern(Long comID, String pattern) {
        List<Invoice> invoices = invoiceRepository.findAllByComIDAndPattern(comID, pattern);
        List<InvoiceDTO> invoiceDTOList = new ArrayList<InvoiceDTO>();
        invoiceDTOList = MappingHelper.map(invoices, InvoiceDTO.class);
        return invoiceDTOList;
    }

    @Override
    public InvoiceDTO getByIdAndComID(Long id, Long comID) {
        Invoice invoice = invoiceRepository.findByIdAndComID(id, comID)
                                    .orElseThrow(() -> new RuntimeException("Not found invoice"));
        return MappingHelper.map(invoice, InvoiceDTO.class);
    }

    @Override
    public Invoice createNew(InvoiceDTO invoiceDTO, User user) {
        String currenKey = "invoice" + user.getLogin() + LocalDateTime.now().toString();
        Invoice newInvoice = new Invoice();
        newInvoice = MappingHelper.map(invoiceDTO, Invoice.class);
        newInvoice.setStatus(InvoiceStatus.New);
        newInvoice.setIkey(new String(Base64.getEncoder().encode(currenKey.getBytes())));
        List<ProductInv> productList = new ArrayList<ProductInv>();
        for(ProductInvDTO prod : invoiceDTO.getProductInvs()){
            ProductInv newProductInv = MappingHelper.map(prod, ProductInv.class);
            productList.add(newProductInv);
        }
        newInvoice.setProductInvs(productList);
        invoiceRepository.save(newInvoice);
        for(ProductInv product : newInvoice.getProductInvs()){
            product.setInvID(newInvoice.getId());
            productInvRepository.save(product);
        }
        return newInvoice;
    }

    @Override
    public Invoice updateInvoice(InvoiceDTO invoiceDTO, User user) {
        Invoice invoice = invoiceRepository.findByIdAndComID(invoiceDTO.getId(), user.getComID())
                                    .orElseThrow(() -> new RuntimeException("Not found invoice"));
        if(invoice.getStatus() == InvoiceStatus.Publish || invoice.getStatus() == InvoiceStatus.Cancel){
            throw new RuntimeException("Không thể sửa hóa đơn này");
        }

        productInvRepository.deleteAllInBatch(invoice.getProductInvs());
        invoice.setCusCode(invoiceDTO.getCusCode());
        invoice.setCusName(invoiceDTO.getCusName());
        invoice.setCusAddress(invoiceDTO.getCusAddress());
        invoice.setCusEmails(invoiceDTO.getCusEmails());
        invoice.setCusPhone(invoice.getCusPhone());
        invoice.setCusTaxCode(invoiceDTO.getCusTaxCode());
        invoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        invoice.setStatus(InvoiceStatus.New);
        invoice.setArisingDate(invoiceDTO.getArisingDate());
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setVatrate(invoiceDTO.getVatrate());
        invoice.setVatAmount(invoiceDTO.getVatAmount());
        invoice.setDiscount(invoiceDTO.getDiscount());
        invoice.setDiscountAmount(invoiceDTO.getDiscountAmount());
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setNote(invoiceDTO.getNote());
        invoice.setExtra(invoiceDTO.getExtra());
        invoice.setCurrenUnit(invoiceDTO.getCurrenUnit());

        List<ProductInv> productList = new ArrayList<ProductInv>();
        for(ProductInvDTO prod : invoiceDTO.getProductInvs()){
            ProductInv newProductInv = MappingHelper.map(prod, ProductInv.class);
            newProductInv.setInvID(invoice.getId());
            productList.add(newProductInv);
            productInvRepository.save(newProductInv);
        }
        invoice.setProductInvs(productList);
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public void deleteMultiple(List<Long> ids, User user) {
        List<Invoice> invoiceList = invoiceRepository.findAllById(ids);
        if(invoiceList.stream().anyMatch( u -> u.getComID() != user.getComID())){
            throw new RuntimeException("Tồn tại sản phẩm không thuộc quyền quản lý của công ty");
        }
        if(invoiceList.stream().anyMatch( u -> u.getStatus() == InvoiceStatus.Publish )){
            throw new RuntimeException("Không thể xóa hóa đơn đã phát hành");
        }

//        List<ProductInv> productInvList = invoiceList.stream().flatMap(inv -> Stream.of(inv.getProductInvs(), null).collect(Collectors.toList())).toList();
//        productInvRepository.deleteAll(productList);
        invoiceRepository.deleteAllInBatch(invoiceList);
    }

    @Override
    public void delete(Long id, User user) {
        Invoice invoice = invoiceRepository.findByIdAndComID(id, user.getComID())
                                    .orElseThrow(() -> new RuntimeException("Not found invoice"));
        if(invoice.getStatus() == InvoiceStatus.Publish ){
            throw new RuntimeException("Không thể xóa hóa đơn đã phát hành");
        }
        productInvRepository.deleteAllInBatch(invoice.getProductInvs().stream().toList());
    }
}
