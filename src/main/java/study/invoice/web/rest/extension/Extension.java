package study.invoice.web.rest.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import study.invoice.domain.User;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.implement.UserServiceImpl;

public class Extension {
    @Autowired
    static UserServiceImpl userService;
    public static User getCurrenUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) auth.getPrincipal();
        User user = userService.findByName(userPrinciple.getUsername()).get();
        return user;
    }
}
