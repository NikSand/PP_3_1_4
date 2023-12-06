package springsecurity.springbootsecurity.dao;

import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserDao {

     List<User> findAll();

     User getById(Long id);

     void save(User user);

     void updateUser(User user, Long id);

     void deleteById(Long id);

     User findByUsername(String username);
}