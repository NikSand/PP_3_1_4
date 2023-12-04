package springsecurity.springbootsecurity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import springsecurity.springbootsecurity.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List <User> findAll() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) throws IllegalArgumentException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select user from User user where user.firstname = : username", User.class)
                .setParameter("username", username)
                .setMaxResults(1).getSingleResult();
    }
}
