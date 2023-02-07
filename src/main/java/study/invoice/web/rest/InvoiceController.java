package study.invoice.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.service.implement.InvoiceServiceImpl;
import study.invoice.service.implement.ProductInvServiceImpl;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    ProductInvServiceImpl productInvService;
}
