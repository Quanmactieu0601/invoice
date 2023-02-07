package study.invoice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.service.implement.TemplateViewServiceImpl;

@RequestMapping("/template")
@RestController
public class TemplateViewController {
    @Autowired
    TemplateViewServiceImpl templateViewService;
}
