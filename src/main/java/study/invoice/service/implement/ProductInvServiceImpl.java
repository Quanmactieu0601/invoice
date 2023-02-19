package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.repository.ProductInvRepository;
import study.invoice.service.ProductInvService;

@Service
public class ProductInvServiceImpl implements ProductInvService {
    @Autowired
    ProductInvRepository productInvRepository;
}
