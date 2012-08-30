/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.vaadin.Application;
import com.vaadin.RootRequiresMoreInformationException;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Root;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless(mappedName = "DeliveryAppBean", name = "DeliveryAppBean")
public class DeliveryAppBean extends Application implements DeliveryAppLocal {

    @EJB
    DeliveryAppRootLocal appRoot;

    @Override
    protected Root getRoot(WrappedRequest request) throws RootRequiresMoreInformationException {
        return appRoot.getAppRoot();
    }

    @Override
    public Application getApplication() {
        return this;
    }
}
