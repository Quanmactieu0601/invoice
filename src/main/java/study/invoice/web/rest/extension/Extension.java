package study.invoice.web.rest.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.domain.User;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.implement.UserServiceImpl;

import javax.annotation.PostConstruct;

@Component
public class Extension {
    private static UserServiceImpl userService;

    @Autowired
    public Extension(UserServiceImpl userService){
        this.userService = userService;
    }

    public static User getCurrenUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) auth.getPrincipal();
        User user = userService.findByName(userPrinciple.getUsername()).get();
        return user;
    }
}
