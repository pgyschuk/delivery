/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.addon.BlackboardLocal;
import com.dkord.security.DeliveryAuthenticationManagerLocal;
import com.dkord.validation.TextFieldValidatorLocal;
import javax.ejb.Local;
import javax.ejb.SessionContext;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface EJBAccessLocal {

    public DeliveryAuthenticationManagerLocal getAuthenticationManager();

    public TextFieldValidatorLocal getTextFieldValidator();

    public UsersServiceLocal getUsersService();

    public RolesServiceLocal getRolesService();

    public BlackboardLocal getBlackboard();

    public SessionContext getContext();
}
