package com.dkord.pages.security;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.dkord.pages.admin.BaseLayout;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Root;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegisterLayout extends VerticalLayout implements View {
    
    @Override
    public void navigateTo(String fragmentParameters) {
    }

    public RegisterLayout(final EJBAccessLocal ejbAccess) {
        super();
        final Panel registerPanel = new Panel();
        registerPanel.setCaption("Register");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);

        final TextField username = new TextField("Name");
        username.addValidator(new StringLengthValidator("Name is too short", 3, 50, false));
        username.setWidth("50%");
        verticalLayout.addComponent(username);

        final TextField email = new TextField("Email");
        email.addValidator(new AbstractStringValidator("User with such email olredy exist") {
            @Override
            protected boolean isValidValue(String value) {
                return ejbAccess.getUsersService().findByEmail(value) == null;
            }
        });
        email.addValidator(new EmailValidator("Email is not valid"));
        email.setWidth("50%");
        verticalLayout.addComponent(email);

        final TextField telephone = new TextField("Telephone");
        telephone.addValidator(new RegexpValidator("(\\+38\\(\\d\\d\\d\\)\\d\\d-\\d\\d-\\d\\d\\d)|()", "Format +38(xxx)xx-xx-xxx"));
        telephone.setWidth("50%");
        verticalLayout.addComponent(telephone);

        final PasswordField password = new PasswordField("Password");
        password.addValidator(new StringLengthValidator("Password is too short", 3, 50, false));
        password.setWidth("50%");
        verticalLayout.addComponent(password);

        final PasswordField retryPassword = new PasswordField("Retry Password");
        retryPassword.addValidator(new AbstractStringValidator("Retry Password is not equal to Password") {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if (!value.equals(password.getValue())) {
                    throw new InvalidValueException("Retry Password is not equal to Password");
                }
            }

            @Override
            protected boolean isValidValue(String value) {
                return value.equals(password.getValue());
            }
        });
        retryPassword.setWidth("50%");
        verticalLayout.addComponent(retryPassword);


        final Button registerButton = new Button("Register");
        registerButton.setStyleName("primary");
        registerButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (ejbAccess.getTextFieldValidator().isValid(registerPanel.getContent())) {
                    User user = new User(username.getValue(), email.getValue(), password.getValue());
                    user.setTelephone(telephone.getValue());
                    ejbAccess.getUsersService().addAuthority(ejbAccess.getUsersService().register(user), Role.Authority.ROLE_USER);
                    removeComponent(registerPanel);
                    addComponent(new LoginLayout(ejbAccess));
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
        
        verticalLayout.addComponent(registerButton);
        verticalLayout.setComponentAlignment(registerButton, Alignment.MIDDLE_RIGHT);
        verticalLayout.addComponent(loginButton);

        registerPanel.setWidth("100%");
        registerPanel.addComponent(verticalLayout);
        addComponent(registerPanel);
        setComponentAlignment(registerPanel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }
}
