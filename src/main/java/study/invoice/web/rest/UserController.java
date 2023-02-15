package study.invoice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.invoice.config.ErrorCode;
import study.invoice.domain.Product;
import study.invoice.domain.User;
import study.invoice.dto.ProductDTO;
import study.invoice.dto.UserDTO;
import study.invoice.dto.respone.ResponeDTO;
import study.invoice.service.implement.UserServiceImpl;
import study.invoice.web.rest.extension.Extension;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        try{
            User user = Extension.getCurrenUser();
            List<UserDTO> userDTOList = userService.getAllByComID(user.getComID());
            return new ResponseEntity<>(userDTOList, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<UserDTO>(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDTO> getUserById(@NotNull @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            UserDTO userDTO = userService.getByIdAndComID(id, user.getComID());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new UserDTO(), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        try{
            User newUser = userService.createUser(userDTO);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.USER_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@NotNull @RequestBody UserDTO userDTO){
        try{
            User user = Extension.getCurrenUser();
            User updateUser = userService.updateProduct(userDTO, user);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.USER_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<ResponeDTO> deleteMultiple(@RequestBody List<Long> ids){
        try{
            User user = Extension.getCurrenUser();
            userService.deleteMultiple(ids, user);
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công " + ids.stream().count() + " user", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.USER_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeDTO> delete(@RequestBody @RequestParam Long id){
        try{
            User user = Extension.getCurrenUser();
            userService.delete(id, user);
            return new ResponseEntity<>(new ResponeDTO(true, null, "Xóa thành công 1 user", null), HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.USER_CODE, ex.getMessage(), null), HttpStatus.OK);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDTO>> filter(@RequestBody @RequestParam UserDTO userDTO){
        try{
            User user = Extension.getCurrenUser();
            List<UserDTO> userDTOList = userService.filter(userDTO, user);
            return new ResponseEntity<>(userDTOList, HttpStatus.OK);
        }
        catch(Exception ex){
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(new ArrayList<UserDTO>(), HttpStatus.OK);
        }
    }
}
