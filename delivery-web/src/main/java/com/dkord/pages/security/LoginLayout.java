package com.dkord.pages.security;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.dkord.navigation.NavigateEvent;
import com.dkord.pages.admin.AdminLayout;
import com.vaadin.Application;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Root;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginLayout extends VerticalLayout{

    private static Logger LOGGER = LoggerFactory.getLogger(LoginLayout.class);

    public LoginLayout(final EJBAccessLocal ejbAccess) {
        super();
        final Panel loginPanel = new Panel();

        loginPanel.setCaption("Please Login");
        ((VerticalLayout) loginPanel.getContent()).setSpacing(true);

        final TextField email = new TextField("Email");

        email.addValidator(new EmailValidator("Email is not valid"));
        email.setValidationVisible(false);
        email.setWidth("100%");
        loginPanel.addComponent(email);

        final PasswordField password = new PasswordField("Password");
        password.setValidationVisible(false);
        password.addValidator(new AbstractStringValidator("Username or password is incorrect") {
            @Override
            protected boolean isValidValue(String value) {
                User user = ejbAccess.getUsersService().findByEmail(email.getValue());
                return user != null && ejbAccess.getUsersService().isPasswordValid(user.getPassword(), value);
            }
        });
        password.setWidth("100%");
        loginPanel.addComponent(password);

        final Button loginButton = new Button("Login");
        loginButton.setStyleName("primary");
        loginPanel.addComponent(loginButton);
        ((VerticalLayout) loginPanel.getContent()).setComponentAlignment(
                loginButton, Alignment.MIDDLE_RIGHT);
        loginButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (ejbAccess.getTextFieldValidator().isValid(loginPanel.getContent())) {
                    final Authentication auth = new UsernamePasswordAuthenticationToken(
                            email.getValue(), password.getValue());
                    try {
                        Authentication returned = ejbAccess.getAuthenticationManager().authenticate(auth);
                        Application.getCurrent().setUser(returned);
                        SecurityContextHolder.getContext().setAuthentication(returned);
                        LOGGER.info("User {} was authenticated", new Object[]{email.getValue()});
                        if (returned.getAuthorities().contains(ejbAccess.getRolesService().getRole(Role.Authority.ROLE_ADMIN))) {
                            Root.getCurrent().setContent(new AdminLayout(ejbAccess));
                        }
                    } catch (Exception e) {
                        LOGGER.error("User {} was NOT authenticated", new Object[]{email.getValue()});
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        });

        Button registerButton = new Button("Register");
        registerButton.addStyleName("link");
        registerButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Root.getCurrent().setContent(new RegisterLayout(ejbAccess));
            }
        });

        loginPanel.addComponent(registerButton);
        loginPanel.setWidth("300px");


        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setSizeFull();
        setMargin(true);
    }
}
