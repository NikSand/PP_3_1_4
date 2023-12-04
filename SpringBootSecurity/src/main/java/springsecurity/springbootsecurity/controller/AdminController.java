package springsecurity.springbootsecurity.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.RoleService;
import springsecurity.springbootsecurity.services.UserService;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@Valid
public class AdminController {

    private final RoleService roleServices;
    private final UserService userServices;
    private static final String REDIRECT = "redirect:/admin";


    public AdminController(RoleService roleServices, UserService userServices) {
        this.roleServices = roleServices;
        this.userServices = userServices;
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userServices.getAllUsers());
        model.addAttribute("username", principal.getName());
        model.addAttribute("role", userServices.findByUsername(principal.getName()).getRoles());
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleServices.getAllRoles());

        return "admin-page";
    }



    @PostMapping("/admin")
    public String create(@ModelAttribute("userUpdate") User user,  @RequestParam(value = "ids", required = false) List<Long> ids) {
        Set<Role> assignedRole = roleServices.findAllRoleId(ids);
        user.setRoles(assignedRole);
        userServices.addUser(user);
        return REDIRECT;
    }


    @PatchMapping("/user/{id}")
    public String edit (@ModelAttribute("user") User user) {
        userServices.updateUser(user);
        return REDIRECT;
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServices.removeUser(id);
        return REDIRECT;
    }

}