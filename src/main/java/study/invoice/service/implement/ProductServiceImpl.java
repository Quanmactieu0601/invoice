package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.Company;
import study.invoice.domain.Product;
import study.invoice.domain.User;
import study.invoice.dto.ProductDTO;
import study.invoice.repository.CompanyRepository;
import study.invoice.repository.ProductRepository;
import study.invoice.service.Mapping.MappingHelper;
import study.invoice.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<ProductDTO> getAllByComID(Long comID) {
        Company currenCom = companyRepository.findById(comID).orElseThrow( () -> new RuntimeException("Can not get curren Company"));
        List<Product>  productList = productRepository.findByComID(comID);
        if(currenCom.getParentComID() != null){
            productList.addAll(productRepository.findByComID(currenCom.getParentComID()));
        }
        return MappingHelper.map(productList, ProductDTO.class);
    }

    @Override
    public ProductDTO getByIdAndComID(Long id, Long comID) {
        Company currenCom = companyRepository.findById(comID).orElseThrow( () -> new RuntimeException("Can not get curren Company"));
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Can not found this product"));
        if(product.getComID() != comID && product.getComID() != currenCom.getParentComID()){
            throw new RuntimeException("Company not has this product");
        }
        return MappingHelper.map(product, ProductDTO.class);
    }

    @Override
    public Product createNew(ProductDTO productDTO, User user) {
        if(productRepository.existsByCodeAndComID(productDTO.getCode(), user.getComID())){
            throw new RuntimeException("Đã tồn tại mã sản phẩm trong hệ thống");
        }

        Product newProduct = new Product();
        newProduct = MappingHelper.map(productDTO, Product.class);
        newProduct.setComID(user.getComID());
        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(ProductDTO productDTO, User user) {
        Product product = productRepository.findByIdAndComID(productDTO.getId(), user.getComID());
        return null;
    }

    @Override
    public void deleteMultiple(List<Long> ids, User user) {

    }

    @Override
    public void delete(Long id, User user) {


    }

    @Override
    public List<ProductDTO> filter(String name, String code, User user) {
        return null;
    }
}
