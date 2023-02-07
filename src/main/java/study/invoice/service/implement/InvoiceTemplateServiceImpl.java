package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.repository.InvoiceTemplateRepository;
import study.invoice.service.InvoiceTemplateService;

@Service
public class InvoiceTemplateServiceImpl implements InvoiceTemplateService {
    @Autowired
    InvoiceTemplateRepository invoiceTemplateRepository;
}
