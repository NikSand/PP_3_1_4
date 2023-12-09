package springsecurity.springbootsecurity.initial;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.repository.RolesRepository;
import springsecurity.springbootsecurity.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitialFile implements ApplicationListener<ContextRefreshedEvent> {

    private final RolesRepository roleRepository;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialFile(RolesRepository roleRepository, UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role role1 = new Role();
        role1.setRole("ROLE_USER");
        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");

        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(role1);
        adminRoles.add(role2);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role1);

        User admin = new User();
        admin.setPassword(passwordEncoder.encode("12345"));
        admin.setRoles(adminRoles);
        admin.setFirstName("Ivan");
        admin.setLastName("Ivanov");
        admin.setEmail("admin@mail.ru");
        admin.setAge((byte) 50);
        userRepository.save(admin);

        User user = new User();
        user.setPassword(passwordEncoder.encode("54321"));
        user.setRoles(userRoles);
        user.setFirstName("Petr");
        user.setLastName("Gluhov");
        user.setEmail("user@mail.ru");
        user.setAge((byte) 22);
        userRepository.save(user);
    }
}
