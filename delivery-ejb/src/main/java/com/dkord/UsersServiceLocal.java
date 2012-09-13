/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Peter Gyschuk
 */
@Remote
public interface UsersServiceLocal {

    User save(User user);
    
    void delete(User user);

    User findByEmail(String email);
    
    boolean isPasswordValid(String encriptedPass, String plainTextPass);
    
    void addAuthority(User user, Role.Authority authority);
    
    boolean hasRole(User user, Role role);
    
    List<User> findAll();
}
