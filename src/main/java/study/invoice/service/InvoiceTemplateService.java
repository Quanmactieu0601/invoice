package study.invoice.service;

import study.invoice.domain.InvoiceTemplate;
import study.invoice.domain.User;
import study.invoice.dto.InvTemplateDTO;

import java.util.List;

public interface InvoiceTemplateService {
    List<InvTemplateDTO> getAll(User user);

    InvoiceTemplate createNew(InvTemplateDTO invTemplateDTO, User user);
}
