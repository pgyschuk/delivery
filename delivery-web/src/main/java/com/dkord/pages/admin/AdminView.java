/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.pages.admin;

import com.dkord.RolesServiceLocal;
import com.dkord.UsersServiceLocal;
import com.vaadin.navigator.View;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Peter Gyschuk
 */
@Stateful
public class AdminView extends VerticalLayout implements AdminViewLocal {

    @EJB
    UsersServiceLocal usersService;

    @EJB
    RolesServiceLocal rolesService;

    public AdminView() {
        super();
        final Panel adminPanel = new Panel();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        adminPanel.setCaption("Admin panel");
        adminPanel.setWidth("900px");
        Panel menuPanel = new Panel();
        menuPanel.setCaption("Menu");
        menuPanel.setWidth("200px");
        Panel contentPanel = new Panel();
        contentPanel.setCaption("Options");
        contentPanel.setWidth("600px");

        Button adminButton = new Button("adminButton", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                printHelloAdmin();
            }
        });
        
        Button userButton = new Button("userButton", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                printHelloUser();
            }
        });

        adminPanel.addComponent(adminButton);
        adminPanel.addComponent(userButton);
        horizontalLayout.addComponent(menuPanel);
        horizontalLayout.addComponent(contentPanel);
        Accordion accordion = new Accordion();
        accordion.setSizeFull();
        Label l1 = new Label("There are no previously saved actions.");
        Label l2 = new Label("There are no saved notes.");
        Label l3 = new Label("There are currently no issues.");

        accordion.addTab(l1, "Saved actions", null);
        accordion.addTab(l2, "Notes", null);
        accordion.addTab(l3, "Issues", null);

        menuPanel.addComponent(accordion);
        adminPanel.addComponent(horizontalLayout);
        addComponent(adminPanel);
        setComponentAlignment(adminPanel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }

    @Override
    public void navigateTo(String fragmentParameters) {
    }

    @Override
    public View getView() {
        return this;
    }
    
    
    private void printHelloAdmin(){
        System.out.println("Hello Admin!");
    }
    
    @RolesAllowed("ROLE_USER")
    @Override
    public void printHelloUser(){
        System.out.println("Hello User!");
    }
}
