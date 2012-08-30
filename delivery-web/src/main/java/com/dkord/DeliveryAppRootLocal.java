/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.vaadin.ui.Root;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface DeliveryAppRootLocal {
    
    Root getAppRoot();
    
}
