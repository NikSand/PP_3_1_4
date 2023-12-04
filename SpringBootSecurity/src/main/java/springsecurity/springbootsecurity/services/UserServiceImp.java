package springsecurity.springbootsecurity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.dao.UserDao;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UserDao userRepository;

    public UserServiceImp(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }


    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
