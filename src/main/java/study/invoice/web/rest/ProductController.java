package study.invoice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import study.invoice.config.ErrorCode;
import study.invoice.domain.Company;
import study.invoice.domain.Product;
import study.invoice.domain.User;
import study.invoice.dto.CompanyDTO;
import study.invoice.dto.ProductDTO;
import study.invoice.dto.respone.ResponeDTO;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.implement.ProductServiceImpl;
import study.invoice.service.implement.UserServiceImpl;
import study.invoice.web.rest.extension.Extension;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        try{
            User user = Extension.getCurrenUser();
            List<ProductDTO> productDTOList = productService.getAllByComID(user.getComID());
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<ProductDTO>(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@NotNull @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            ProductDTO productDTO = productService.getByIdAndComID(id, user.getComID());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO){
        try{
            User user = Extension.getCurrenUser();
            productDTO.setComID(user.getComID());
            Product product = productService.createNew(productDTO, user);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyDTO){
//        try{
//            Company company = companyService.createParentCompany(companyDTO);
//            return new ResponseEntity<>(company, HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
//        }
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@NotNull @RequestBody ProductDTO productDTO){
        try{
            User user = Extension.getCurrenUser();
            Product product = productService.updateProduct(productDTO, user);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<ResponeDTO> deleteMultiple(@RequestBody List<Long> ids){
        try{
            User user = Extension.getCurrenUser();
            productService.deleteMultiple(ids, user);
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công " + ids.stream().count() + " sản phẩm", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeDTO> delete(@RequestBody @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            productService.delete(id, user);
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công 1 sản phẩm", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filter(@RequestBody @RequestParam String name, @RequestParam String code){
        try{
            User user = Extension.getCurrenUser();
            List<ProductDTO> productDTOList = productService.filter(name, code, user);
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<ProductDTO>(), HttpStatus.OK);
        }
    }
}
