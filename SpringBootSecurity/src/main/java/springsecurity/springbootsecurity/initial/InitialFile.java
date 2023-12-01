package springsecurity.springbootsecurity.initial;

import org.springframework.stereotype.Component;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.RoleService;
import springsecurity.springbootsecurity.services.UserService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialFile {

    private final UserService userService;

    private final RoleService roleService;

    public InitialFile(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
    }

    @Transactional
    @PostConstruct
    public void run() {

        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));

        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        adminRole.add(roleService.getRoleById(1L));
        userRole.add(roleService.getRoleById(2L));

        userService.addUser(new User("admin", "admin", (byte) 35, "domrachev.42@mail.ru",  "12345", adminRole));
        userService.addUser(new User("user", "user", (byte) 30, "sidorov-pasha@yandex.com",  "54321", userRole));
        }
}
