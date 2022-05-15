package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserName(String name) {
        return (User) entityManager.createQuery("select u from User u where u.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveNewUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User userUpdate) {
        entityManager.merge(userUpdate);
    }

    @Override
    public void deleteUserById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
