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
    public void save(User user) {
        entityManager.persist(user);
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
    public User register(User user) {
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), SALT));
        entityManager.persist(user);
        return user;
    }

    @Override
    public void addAuthority(User user, Role.Authority authority) {
        Role role = rolesServiceBean.getRole(authority);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        entityManager.merge(user);
    }
}
