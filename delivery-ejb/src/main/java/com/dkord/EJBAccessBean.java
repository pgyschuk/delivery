/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.addon.BlackboardLocal;
import com.dkord.security.DeliveryAuthenticationManagerLocal;
import com.dkord.validation.TextFieldValidatorLocal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class EJBAccessBean implements EJBAccessLocal {

    @EJB
    private DeliveryAuthenticationManagerLocal authenticationManager;

    @EJB
    private TextFieldValidatorLocal textFieldValidator;

    @EJB
    private UsersServiceLocal usersService;

    @EJB
    private RolesServiceLocal rolesService;

    @EJB
    private BlackboardLocal blackboard;

    @Resource
    SessionContext context;

    @Override
    public DeliveryAuthenticationManagerLocal getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    public TextFieldValidatorLocal getTextFieldValidator() {
        return textFieldValidator;
    }

    @Override
    public UsersServiceLocal getUsersService() {
        return usersService;
    }

    @Override
    public RolesServiceLocal getRolesService() {
        return rolesService;
    }

    @Override
    public BlackboardLocal getBlackboard() {
        return blackboard;
    }

    @Override
    public SessionContext getContext() {
        return context;
    }
}
