/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.pages.admin;

import com.vaadin.Application;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Peter Gyschuk
 */
public class BaseLayout extends VerticalLayout{

    public BaseLayout(AbstractOrderedLayout mainLayout) {
        this.mainLayout = mainLayout;
        Panel mainPanel = new Panel();
        mainPanel.setWidth("1024px");
        
        
        Button logoutButton = new Button("LogOut");
        logoutButton.addStyleName("link");
        logoutButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Application.getCurrent().close();
                Application.getCurrent().init();
            }
        });
        
        mainPanel.addComponent(logoutButton);
        mainPanel.addComponent(mainLayout);
        addComponent(mainPanel);
        setComponentAlignment(mainPanel, Alignment.TOP_CENTER);
        setMargin(true);
    }
    
    AbstractOrderedLayout mainLayout;
    
}
