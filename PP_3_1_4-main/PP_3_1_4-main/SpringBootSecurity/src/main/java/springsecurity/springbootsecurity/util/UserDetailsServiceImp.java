package springsecurity.springbootsecurity.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.services.UserService;
import javax.transaction.Transactional;


    @Service
    public class UserDetailsServiceImp implements UserDetailsService {

        private final UserService userService;

        public UserDetailsServiceImp (UserService userService) {
            this.userService = userService;
        }

        @Override
        @Transactional
        public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("User '%s' не найден", username));
            }
            return user;
        }
}
