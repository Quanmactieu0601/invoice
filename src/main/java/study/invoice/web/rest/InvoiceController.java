package study.invoice.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.domain.User;
import study.invoice.dto.InvoiceDTO;
import study.invoice.service.implement.InvoiceServiceImpl;
import study.invoice.service.implement.ProductInvServiceImpl;
import study.invoice.web.rest.extension.Extension;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {
    private final Logger log = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    ProductInvServiceImpl productInvService;

    @GetMapping("/get-all")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoice(@NotNull @RequestParam String pattern){
        try{
            User user = Extension.getCurrenUser();
            List<InvoiceDTO> invoiceDTOList = invoiceService.getAllByComIDAndPattern(user.getComID(), pattern);
            return new ResponseEntity<>(invoiceDTOList, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<InvoiceDTO>(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@NotNull @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            InvoiceDTO invoiceDTO = invoiceService.getByIdAndComID(id, user.getComID());
            return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new InvoiceDTO(), HttpStatus.OK);
        }
    }

    @GetMapping("/viewer/{id}")
    public ResponseEntity<?> getProductById(@NotNull @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            InvoiceDTO invoiceDTO = invoiceService.getByIdAndComID(id, user.getComID());
            return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new InvoiceDTO(), HttpStatus.OK);
        }
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createParentCompany(@Valid @RequestBody ProductDTO productDTO){
//        try{
//            User user = Extension.getCurrenUser();
//            Product product = invoiceService.createNew(productDTO, user);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
//        }
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<?> updateCompany(@NotNull @RequestBody ProductDTO productDTO){
//        try{
//            User user = Extension.getCurrenUser();
//            Product product = invoiceService.updateProduct(productDTO, user);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
//        }
//    }
//
//    @DeleteMapping("/delete-multiple")
//    public ResponseEntity<ResponeDTO> deleteMultiple(@RequestBody List<Long> ids){
//        try{
//            User user = Extension.getCurrenUser();
//            invoiceService.deleteMultiple(ids, user);
//            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công " + ids.stream().count() + " sản phẩm", null), HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<ResponeDTO> delete(@RequestBody @RequestParam Long id){
//        try{
//            User user = Extension.getCurrenUser();
//            invoiceService.delete(id, user);
//            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công 1 sản phẩm", null), HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.PRODUCT_CODE, ex.getMessage(), null), HttpStatus.OK);
//        }
//    }
//
//    @GetMapping("/filter")
//    public ResponseEntity<List<ProductDTO>> filter(@RequestBody @RequestParam String name, @RequestParam String code){
//        try{
//            User user = Extension.getCurrenUser();
//            List<ProductDTO> productDTOList = invoiceService.filter(name, code, user);
//            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
//        }
//        catch(Exception ex){
//            log.error(ex.getMessage(), ex);
//            return new ResponseEntity<>(new ArrayList<ProductDTO>(), HttpStatus.OK);
//        }
//    }
}
