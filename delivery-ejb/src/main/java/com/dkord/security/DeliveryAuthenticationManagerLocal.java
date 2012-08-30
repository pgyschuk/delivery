/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.security;

import javax.ejb.Local;
import org.springframework.security.authentication.AuthenticationManager;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface DeliveryAuthenticationManagerLocal extends AuthenticationManager{
    
}
