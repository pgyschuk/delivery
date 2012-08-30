/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.pages.security;

import com.vaadin.navigator.View;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface LoginViewLocal extends View{
    View getView();
}
