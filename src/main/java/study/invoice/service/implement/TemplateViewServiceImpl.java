package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.repository.TemplateViewRepository;
import study.invoice.service.TemplateViewService;

@Service
public class TemplateViewServiceImpl implements TemplateViewService {
    @Autowired
    TemplateViewRepository templateViewRepository;
}
