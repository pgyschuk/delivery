/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.pages.admin;

import com.vaadin.navigator.View;
import javax.ejb.Local;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface AdminViewLocal extends View{
    View getView();
    
    @PreAuthorize("hasRole('ROLE_USER')")
    public void printHelloUser();
}
