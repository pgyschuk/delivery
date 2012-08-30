/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.datamodel.Role;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface RolesServiceLocal {
    Role getRole(Role.Authority authority);
}
