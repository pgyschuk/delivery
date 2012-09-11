package com.dkord.pages.security;

import com.dkord.EJBAccessLocal;
import com.dkord.components.UserEditComponent;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.dkord.pages.admin.BaseLayout;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;

public class RegisterLayout extends VerticalLayout implements View {
    
    @Override
    public void navigateTo(String fragmentParameters) {
    }

    public RegisterLayout(final EJBAccessLocal ejbAccess, User user) {
        super();
        final Panel registerPanel = new Panel();
        registerPanel.setCaption("Register");
        final UserEditComponent userEditComponent = new UserEditComponent(user);

        final Button registerButton = new Button("Register");
        registerButton.setStyleName("primary");
        registerButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (ejbAccess.getTextFieldValidator().isValid(registerPanel.getContent())) {
                    ejbAccess.getUsersService().addAuthority(ejbAccess.getUsersService().register(userEditComponent.getUser()), Role.Authority.ROLE_USER);
                    Root.getCurrent().setContent(new BaseLayout(new LoginLayout(ejbAccess)));
                }
            }
        });
        
        Button loginButton = new Button("Login");
        loginButton.addStyleName("link");
        loginButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Root.getCurrent().setContent(new BaseLayout(new LoginLayout(ejbAccess)));
            }
        });
        
        registerPanel.setWidth("100%");
        registerPanel.addComponent(userEditComponent);
        registerPanel.addComponent(registerButton);
        registerPanel.addComponent(loginButton);
        
        addComponent(registerPanel);
        setComponentAlignment(registerPanel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }
}
