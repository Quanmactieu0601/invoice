package study.invoice.service;

import study.invoice.dto.TemplateViewDTO;

import java.util.List;

public interface TemplateViewService {
    List<TemplateViewDTO> getAll();
}
