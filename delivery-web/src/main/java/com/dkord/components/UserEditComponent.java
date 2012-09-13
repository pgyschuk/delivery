package com.dkord.components;

import com.dkord.datamodel.Contacts;
import com.dkord.datamodel.User;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 *
 * @author Peter Gyschuk
 */
public class UserEditComponent extends HorizontalLayout {

    private User user;

    private TextField username;

    private PasswordField password;

    private PasswordField retryPassword;

    private ContactsEditComponent contactsEditComponent;

    private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    private final static String SALT = "DeLiVeRyProJeCt";

    public UserEditComponent(User user) {
        setSpacing(true);
        setWidth("100%");
        this.user = user;
        contactsEditComponent = new ContactsEditComponent(user.getContacts() != null ? user.getContacts() : new Contacts());
        VerticalLayout verticalLayout = new VerticalLayout();
//        final TextField email = new TextField("Email", user.getEmail());
//        email.addValidator(new AbstractStringValidator("User with such email olredy exist") {
//            @Override
//            protected boolean isValidValue(String value) {
//                return ejbAccess.getUsersService().findByEmail(value) == null;
//            }
//        });
//        email.addValidator(new EmailValidator("Email is not valid"));
//        email.setWidth("50%");
//        verticalLayout.addComponent(email);

//        final TextField telephone = new TextField("Telephone", user.getTelephone());
//        telephone.addValidator(new RegexpValidator("(\\+38\\(\\d+)\\d+)|()", "Format +38(<code>)<number>"));
//        telephone.setWidth("50%");
//        verticalLayout.addComponent(telephone);

        username = new TextField("Name", user.getName());
        username.addValidator(new StringLengthValidator("Name mast be 3-50 characters", 3, 50, false));
        username.setWidth("100%");

        password = new PasswordField("Password");
        password.addValidator(new StringLengthValidator("Password is too short", 0, 20, true));
        password.setWidth("100%");

        retryPassword = new PasswordField("Retry Password");
        retryPassword.addValidator(new AbstractStringValidator("Retry Password is not equal to Password") {
            @Override
            public void validate(Object value) throws InvalidValueException {
                if (!(StringUtils.isBlank((String)value) && StringUtils.isBlank(password.getValue())) || !value.equals(password.getValue())) {
                    throw new InvalidValueException("Retry Password is not equal to Password");
                }
            }

            @Override
            protected boolean isValidValue(String value) {
                return value.equals(password.getValue());
            }
        });
        retryPassword.setWidth("100%");

        verticalLayout.addComponent(username);
        verticalLayout.addComponent(password);
        verticalLayout.addComponent(retryPassword);
        addComponent(verticalLayout);
        addComponent(contactsEditComponent);
    }

    public User getUser() {
        user.setName(username.getValue());
        if (StringUtils.isNotBlank(password.getValue())) {
            user.setPassword(passwordEncoder.encodePassword(password.getValue(), SALT));
        }
        user.setContacts(contactsEditComponent.getContacts());
        return user;
    }
}
