package com.dkord.pages;

import com.dkord.EJBAccessLocal;
import com.dkord.components.UserEditComponent;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.dkord.pages.admin.BaseLayout;
import com.dkord.pages.security.LoginLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;

public class UserEditLayout extends Configuration {

    private EJBAccessLocal ejbAccess;

    private VerticalLayout userInfoLayout;

    private UserEditComponent userEditComponent;

    public UserEditLayout(final EJBAccessLocal ejbAccess, User user) {
        super();
        this.ejbAccess = ejbAccess;
        userInfoLayout = new VerticalLayout();
        userEditComponent = new UserEditComponent(user);

        userInfoLayout.setWidth("100%");
        userInfoLayout.addComponent(userEditComponent);
        userInfoLayout.setSpacing(true);
        addComponent(userInfoLayout);
        setComponentAlignment(userInfoLayout, Alignment.MIDDLE_CENTER);
        setMargin(true);
        setSpacing(true);
    }

    @Override
    public void save() {
        if (ejbAccess.getTextFieldValidator().isValid(userInfoLayout)) {
            ejbAccess.getUsersService().addAuthority(ejbAccess.getUsersService().register(userEditComponent.getUser()), Role.Authority.ROLE_USER);
            Root.getCurrent().setContent(new BaseLayout(ejbAccess, new LoginLayout(ejbAccess)));
        }
    }

    @Override
    public String getName() {
        return "User information";
    }
}
