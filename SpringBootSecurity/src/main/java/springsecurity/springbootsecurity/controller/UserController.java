package springsecurity.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springsecurity.springbootsecurity.services.UserService;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getAllUsers(Principal principal, Model model ) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "/user";
    }

}