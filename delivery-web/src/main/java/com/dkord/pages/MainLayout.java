package com.dkord.pages;

import com.dkord.pages.BaseLayout;
import com.dkord.EJBAccessLocal;
import com.dkord.pages.Configuration;
import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Peter Gyschuk
 */
public class MainLayout extends VerticalLayout {

    public MainLayout(final EJBAccessLocal ejbAccess, final Configuration configuration) {
        super();
        final Panel mainPanel = new Panel();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        mainPanel.setCaption("Delivery");
        mainPanel.setWidth("900px");
        Panel menuPanel = new Panel();
        menuPanel.setCaption("Menu");
        menuPanel.setWidth("200px");
        final Panel contentPanel = new Panel();
        contentPanel.setCaption(configuration.getName());
        contentPanel.setWidth("660px");
        contentPanel.addComponent(configuration);
        final Authentication authentication = (Authentication) Application.getCurrent().getUser();
        if (authentication != null) {
            horizontalLayout.addComponent(menuPanel);
        }
        horizontalLayout.addComponent(contentPanel);

        VerticalLayout userAction = new VerticalLayout();

        Button saveButton = new Button("Save");
        saveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                configuration.save();
            }
        });

        Button assignRoleButton = new Button("Assign role");
        assignRoleButton.addStyleName("link");
        assignRoleButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                MainLayout adminLayout = new MainLayout(ejbAccess, new UserEditLayout(ejbAccess));
                Root.getCurrent().setContent(new BaseLayout(ejbAccess, adminLayout));
            }
        });
        userAction.addComponent(assignRoleButton);

        menuPanel.addComponent(userAction);
        mainPanel.addComponent(horizontalLayout);
        mainPanel.addComponent(saveButton);
        addComponent(mainPanel);
        setComponentAlignment(mainPanel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }
}
