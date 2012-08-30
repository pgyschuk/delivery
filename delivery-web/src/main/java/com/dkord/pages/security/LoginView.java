package com.dkord.pages.security;

import com.dkord.RolesServiceLocal;
import com.dkord.UsersServiceLocal;
import com.dkord.addon.BlackboardLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.dkord.navigation.NavigateEvent;
import com.dkord.security.DeliveryAuthenticationManagerLocal;
import com.dkord.validation.TextFieldValidatorLocal;
import com.vaadin.Application;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Stateful
public class LoginView extends VerticalLayout implements LoginViewLocal {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginView.class);

    @EJB
    private DeliveryAuthenticationManagerLocal authenticationManager;

    @EJB
    private TextFieldValidatorLocal textFieldValidator;

    @EJB
    private UsersServiceLocal usersService;

    @EJB
    private RolesServiceLocal rolesService;

    @EJB
    private BlackboardLocal blackboard;
    
    @Resource
    SessionContext context;


    @Override
    public View getView() {
        return this;
    }

    @Override
    public void navigateTo(String fragmentParameters) {
    }

    public LoginView() {
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
                User user = usersService.findByEmail(email.getValue());
                return user != null && usersService.isPasswordValid(user.getPassword(), value);
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
                if (textFieldValidator.isValid(loginPanel.getContent())) {
                    final Authentication auth = new UsernamePasswordAuthenticationToken(
                            email.getValue(), password.getValue());
                    try {
                        Authentication returned = authenticationManager.authenticate(auth);
                        Application.getCurrent().setUser(returned);
                        SecurityContextHolder.getContext().setAuthentication(returned);
                        LOGGER.info("User {} was authenticated", new Object[]{email.getValue()});
                        if (returned.getAuthorities().contains(rolesService.getRole(Role.Authority.ROLE_ADMIN))) {
                            blackboard.fire(new NavigateEvent("Admin"));
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
                blackboard.fire(new NavigateEvent("Register"));
            }
        });

        loginPanel.addComponent(registerButton);
        loginPanel.setWidth("300px");


        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setSizeFull();
        setMargin(true);
//        setSizeFull();
    }
}
