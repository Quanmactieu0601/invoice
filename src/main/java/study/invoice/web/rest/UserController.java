package study.invoice.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.invoice.service.implement.UserServiceImpl;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
}
