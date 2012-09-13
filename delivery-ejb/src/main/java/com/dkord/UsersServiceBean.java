/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class UsersServiceBean implements UsersServiceLocal {

    private final static String SALT = "DeLiVeRyProJeCt";

    @PersistenceContext(name = "delivery-persistence-unit")
    private EntityManager entityManager;

    @EJB
    RolesServiceLocal rolesServiceBean;

    private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public User save(User user) {
        user = entityManager.merge(user);
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = entityManager.createNamedQuery("findByEmail", User.class).setParameter("email", email).getResultList();
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public boolean isPasswordValid(String encriptedPass, String plainTextPass) {
        return passwordEncoder.isPasswordValid(encriptedPass, plainTextPass, SALT);
    }

    @Override
    public void addAuthority(User user, Role.Authority authority) {
        user = entityManager.merge(user);
        Role role = rolesServiceBean.getRole(authority);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        entityManager.persist(user);
    }

    @Override
    public boolean hasRole(User user, Role role) {
        return user.getRoles().contains(role);
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
        return users;
    }

    @Override
    public void delete(User user) {
        user = entityManager.merge(user);
        entityManager.remove(user);
    }
}
