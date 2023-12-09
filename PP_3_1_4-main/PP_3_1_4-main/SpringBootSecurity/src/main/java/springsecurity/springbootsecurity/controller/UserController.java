package springsecurity.springbootsecurity.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    // Principal нужен для того, чтобы мы могли отобразить данные
   //  мы можем вызвать принципал из любого места кода, так как он есть в контексте(в текущей сессии пользователя)

    @GetMapping("")
    public String showUserAcc(Principal principal, Model model) {

        User user =  userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userRoles", user.getRoles()); //
        return "/user";
    }

}