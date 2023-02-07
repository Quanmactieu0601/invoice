package study.invoice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.dto.ProductDTO;
import study.invoice.dto.TemplateViewDTO;
import study.invoice.service.implement.TemplateViewServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/template")
@RestController
public class TemplateViewController {
    private final Logger log = LoggerFactory.getLogger(TemplateViewController.class);
    @Autowired
    TemplateViewServiceImpl templateViewService;

    @GetMapping("/get-all")
    public ResponseEntity<List<TemplateViewDTO>> getAll(){
        try{
            List<TemplateViewDTO> templateViewDTOList = templateViewService.getAll();
            return new ResponseEntity<>(templateViewDTOList, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<TemplateViewDTO>(), HttpStatus.OK);
        }
    }
}
