/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.datamodel.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class RolesServiceBean implements RolesServiceLocal {

    @PersistenceContext(name = "delivery-persistence-unit")
    private EntityManager entityManager;

    @Override
    public Role getRole(Role.Authority authority) {
        return entityManager.createNamedQuery("findByAuthority", Role.class).setParameter("authority", authority.name()).getSingleResult();
    }
}
