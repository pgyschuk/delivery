package com.dkord;

import com.vaadin.Application;
import com.vaadin.RootRequiresMoreInformationException;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Root;

/**
 *
 * @author Peter Gyschuk
 */
public class DeliveryApp extends Application {

    EJBAccessLocal ejbAccess;

    public DeliveryApp(EJBAccessLocal ejbAccess) {
        this.ejbAccess = ejbAccess;
    }

    @Override
    protected Root getRoot(WrappedRequest request) throws RootRequiresMoreInformationException {
        return new DeliveryAppRoot(ejbAccess);
    }
}
