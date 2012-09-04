/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.pages.security.LoginLayout;
import com.vaadin.Application;
import com.vaadin.terminal.WrappedRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Peter Gyschuk
 */
@Stateful
public class DeliveryAppRootBean extends Root implements DeliveryAppRootLocal {

    @EJB
    EJBAccessLocal ejbAccess;

    @Override
    public Root getAppRoot() {
        return this;
    }

    @Override
    protected void init(WrappedRequest request) {
        setContent(new LoginLayout(ejbAccess));
        setSizeFull();
    }
}
