package com.dkord;

import com.dkord.pages.BaseLayout;
import com.dkord.pages.security.LoginLayout;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Root;

/**
 *
 * @author Peter Gyschuk
 */
public class DeliveryAppRoot extends Root {

    EJBAccessLocal ejbAccess;

    public DeliveryAppRoot(EJBAccessLocal ejbAccess) {
        this.ejbAccess = ejbAccess;
    }

    
    @Override
    protected void init(WrappedRequest request) {
        setContent(new BaseLayout(ejbAccess, new LoginLayout(ejbAccess)) );
        setSizeFull();
    }
}
