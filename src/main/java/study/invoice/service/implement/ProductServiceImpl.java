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
        if(user.getComID() == null){
            throw new RuntimeException("Tài khoản không có quyền tạo sản phẩm");
        }
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
        if(product == null){
            throw new RuntimeException("Sản phẩm không tồn tại");
        }
        product.setCode(productDTO.getCode());
        product.setPrice(productDTO.getPrice());
        product.setGroupCode(product.getGroupCode());
        product.setName(product.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setUnit(product.getUnit());
        product.setVatrate(product.getVatrate());
        product.setVatrateOther(productDTO.getVatrateOther());
        productRepository.save(product);

        return product;
    }

    @Override
    public void deleteMultiple(List<Long> ids, User user) {
        List<Product> productList = productRepository.findAllById(ids);
        if(productList.stream().anyMatch( u -> u.getComID() != user.getComID())){
            throw new RuntimeException("Tồn tại sản phẩm không thuộc quyền quản lý của công ty");
        }

        productRepository.deleteAll(productList);
    }

    @Override
    public void delete(Long id, User user) {
        Product product = productRepository.findByIdAndComID(id, user.getComID());
        if(product != null){
            productRepository.delete(product);
        }
    }

    @Override
    public List<ProductDTO> filter(String name, String code, User user) {
        return null;
    }
}
