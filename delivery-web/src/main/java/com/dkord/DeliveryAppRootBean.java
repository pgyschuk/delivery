/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord;

import com.dkord.addon.BlackboardLocal;
import com.dkord.navigation.NavigateEvent;
import com.dkord.navigation.NavigateListener;
import com.dkord.pages.admin.AdminViewLocal;
import com.dkord.pages.security.LoginViewLocal;
import com.dkord.pages.security.RegisterViewLocal;
import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.Application;
import com.vaadin.navigator.Navigator;
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
    private LoginViewLocal loginView;

    @EJB
    private RegisterViewLocal registerView;

    @EJB
    private AdminViewLocal adminView;

    @EJB
    private BlackboardLocal blackboard;

    @Override
    public Root getAppRoot() {
        return this;
    }

    @Override
    protected void init(WrappedRequest request) {
        Panel contentPanel = new Panel();

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        Panel mainPanel = new Panel();
        MenuBar bar = new MenuBar();

        layout.addComponent(bar);
        layout.addComponent(contentPanel);
        mainPanel.addComponent(layout);
        setContent(mainPanel);
        ((VerticalLayout) mainPanel.getContent()).setComponentAlignment(
                layout, Alignment.TOP_CENTER);
        setSizeFull();
        final Navigator mainNavigator = new Navigator(contentPanel);

        mainNavigator.addView("Login", loginView.getView());
        mainNavigator.addView("Register", registerView.getView());
        mainNavigator.addView("Admin", adminView.getView());

        blackboard.setBlackboard(new Blackboard());
        blackboard.register(NavigateListener.class, NavigateEvent.class);
        blackboard.addListener(new NavigateListener() {
            @Override
            public void navigate(NavigateEvent event) {
                mainNavigator.navigateTo(event.getNavigateTo());
            }
        });

        bar.addItem("Login", new Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                blackboard.fire(new NavigateEvent("Login"));
            }
        });
        bar.addItem("LoginOut", new Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Application.getCurrent().close();
            }
        });

    }
}
