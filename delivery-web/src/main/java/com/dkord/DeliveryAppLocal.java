/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.vaadin.Application;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface DeliveryAppLocal {
    Application getApplication();
}
