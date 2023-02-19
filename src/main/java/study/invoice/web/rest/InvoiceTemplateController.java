package study.invoice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.invoice.config.ErrorCode;
import study.invoice.domain.InvoiceTemplate;
import study.invoice.domain.User;
import study.invoice.dto.InvTemplateDTO;
import study.invoice.dto.respone.ResponeDTO;
import study.invoice.service.implement.InvoiceTemplateServiceImpl;
import study.invoice.web.rest.extension.Extension;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/invtemplate")
@RestController
public class InvoiceTemplateController {
    private final Logger log = LoggerFactory.getLogger(InvoiceTemplateController.class);
    @Autowired
    InvoiceTemplateServiceImpl invoiceTemplateService;

    @GetMapping("/get-all")
    public ResponseEntity<List<InvTemplateDTO>> getAll(){
        try{
            User user = Extension.getCurrenUser();
            List<InvTemplateDTO> listInvTemplateDTO = invoiceTemplateService.getAll(user);
            return new ResponseEntity<>(listInvTemplateDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<InvTemplateDTO>(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@NotNull @RequestBody InvTemplateDTO invTemplateDTO){
        try{
            User user = Extension.getCurrenUser();
            InvoiceTemplate invoiceTemplate = invoiceTemplateService.createNew(invTemplateDTO, user);
            return new ResponseEntity<>(new ResponeDTO(true, null, "Tạo mới thành công 1 mẫu số", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.TEMPLATE_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }
}
