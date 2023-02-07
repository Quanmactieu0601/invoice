package study.invoice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.config.ErrorCode;
import study.invoice.dto.request.LoginForm;
import study.invoice.dto.respone.JwtRespone;
import study.invoice.dto.respone.ResponeDTO;
import study.invoice.security.jwt.TokenProvider;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.implement.RoleServiceImpl;
import study.invoice.service.implement.UserServiceImpl;

import javax.validation.Valid;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> register(@Valid @RequestBody LoginForm loginForm){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getLogin(),
                    loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            UserPrinciple user = (UserPrinciple) authentication.getPrincipal();
            return new ResponseEntity<>(new JwtRespone("Bearer " + token, "Bearer", user.getUsername(), user.getAuthorities()), HttpStatus.OK);
        }
        catch (BadCredentialsException badCredentialsException){
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.AUTH_CODE, "Tên đăng nhập hoặc mật khẩu không đúng", null), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ResponeDTO(false, ErrorCode.AUTH_CODE, "Vui lòng nhập đúng tên đăng nhập hoặc mật khẩu", null), HttpStatus.OK);
        }

    }

}
