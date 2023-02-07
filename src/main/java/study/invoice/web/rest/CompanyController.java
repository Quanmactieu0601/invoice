package study.invoice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import study.invoice.config.ErrorCode;
import study.invoice.domain.Company;
import study.invoice.domain.User;
import study.invoice.dto.CompanyDTO;
import study.invoice.dto.respone.ResponeDTO;
import study.invoice.security.jwt.JwtTokenFilter;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.implement.CompanyServiceImpl;
import study.invoice.service.implement.UserServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/company")
@RestController
public class CompanyController {
    private final Logger log = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    CompanyServiceImpl companyService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getCompanyInfo(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrinciple user = (UserPrinciple) auth.getPrincipal();
            List<CompanyDTO> companyDTOList = companyService.getCompanyByUser(userService.findByName(user.getUsername()).get());
            return new ResponseEntity<>(companyDTOList, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCompanyById(@NotNull @RequestParam Long id){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrinciple user = (UserPrinciple) auth.getPrincipal();
            CompanyDTO companyDTO = companyService.getCompanyById(id, userService.findByName(user.getUsername()).get());
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @PostMapping("/create-parent-company")
    public ResponseEntity<?> createParentCompany(@Valid @RequestBody CompanyDTO companyDTO){
        try{
            Company company = companyService.createParentCompany(companyDTO);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @PostMapping("/create-company")
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyDTO companyDTO){
        try{
            Company company = companyService.createParentCompany(companyDTO);
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@NotNull @RequestBody CompanyDTO companyDTO){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrinciple user = (UserPrinciple) auth.getPrincipal();
            Company company = companyService.updateCompany(companyDTO, userService.findByName(user.getUsername()).get());
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<ResponeDTO> deleteMultiple(@RequestBody List<Long> ids){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrinciple user = (UserPrinciple) auth.getPrincipal();
            companyService.deleteMultiple(ids, userService.findByName(user.getUsername()).get());
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công " + ids.stream().count() + " công ty", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeDTO> deleteMultiple(@RequestBody @RequestParam Long id){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserPrinciple user = (UserPrinciple) auth.getPrincipal();
            companyService.delete(id, userService.findByName(user.getUsername()).get());
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công 1 công ty", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.COMPANY_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

}
