package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.TemplateView;
import study.invoice.dto.TemplateViewDTO;
import study.invoice.repository.TemplateViewRepository;
import study.invoice.service.Mapping.MappingHelper;
import study.invoice.service.TemplateViewService;

import java.util.List;

@Service
public class TemplateViewServiceImpl implements TemplateViewService {
    @Autowired
    TemplateViewRepository templateViewRepository;

    @Override
    public List<TemplateViewDTO> getAll() {
        List<TemplateView> templateViews = templateViewRepository.findAll();
        return MappingHelper.map(templateViews, TemplateViewDTO.class);
    }
}
