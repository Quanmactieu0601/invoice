package study.invoice.service;

import study.invoice.domain.Product;
import study.invoice.domain.User;
import study.invoice.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllByComID(Long comID);

    ProductDTO getByIdAndComID(Long id, Long comID);

    Product createNew(ProductDTO productDTO, User user);

    Product updateProduct(ProductDTO productDTO, User user);

    void deleteMultiple(List<Long> ids, User user);

    void delete(Long id, User user);

    List<ProductDTO> filter(String name, String code, User user);
}
