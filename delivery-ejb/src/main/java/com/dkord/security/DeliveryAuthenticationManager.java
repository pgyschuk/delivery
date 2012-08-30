/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.security;

import com.dkord.UsersServiceLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class DeliveryAuthenticationManager implements DeliveryAuthenticationManagerLocal {

    @EJB
    UsersServiceLocal usersService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        User user = null;

        try {
            user = usersService.findByEmail(auth.getName());
        } catch (Exception e) {
            throw new RuntimeException("User is null!");
        }

        if (usersService.isPasswordValid(user.getPassword(), (String) auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(
                    auth.getName(),
                    auth.getCredentials(),
                    getAuthorities(user.getRoles()));
        }
        throw new RuntimeException("User is not authenticated!");
    }

    public Collection<Role> getAuthorities(Set<Role> roles) {
        List<Role> authList = new ArrayList<Role>();

        for (Role role : roles) {
            authList.add(role);
        }
        return authList;
    }
}
