package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.repository.InvoiceRepository;
import study.invoice.repository.ProductInvRepository;
import study.invoice.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductInvRepository productInvRepository;
}
