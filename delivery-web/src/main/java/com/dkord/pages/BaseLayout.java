package com.dkord.pages;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.User;
import com.dkord.pages.UserContactsLayout;
import com.dkord.pages.security.LoginLayout;
import com.vaadin.Application;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Root;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Peter Gyschuk
 */
public class BaseLayout extends VerticalLayout {

    public BaseLayout(final EJBAccessLocal ejbAccess, final AbstractOrderedLayout mainLayout) {
        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setSpacing(true);
        Panel mainPanel = new Panel();
        mainPanel.setWidth("1024px");

        final Authentication authentication = (Authentication) Application.getCurrent().getUser();
        if (authentication != null) {
            final User user = ejbAccess.getUsersService().findByEmail(authentication.getName());
            Button logoutButton = new Button("LogOut");
            logoutButton.addStyleName("link");
            logoutButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Application.getCurrent().close();
                    Application.getCurrent().init();
                }
            });

            Button userButton = new Button(user.getName());
            userButton.addStyleName("link");
            userButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    MainLayout adminLayout = new MainLayout(ejbAccess,new UserContactsLayout(ejbAccess, user));
                    Root.getCurrent().setContent(new BaseLayout(ejbAccess, adminLayout));
                }
            });
            menuLayout.addComponent(logoutButton);
            menuLayout.addComponent(userButton);
        } else {
            Button loginButton = new Button("Login");
            loginButton.addStyleName("link");
            loginButton.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Root.getCurrent().setContent(new BaseLayout(ejbAccess, new LoginLayout(ejbAccess)));
                }
            });
            menuLayout.addComponent(loginButton);
        }

        mainPanel.addComponent(menuLayout);
        mainPanel.addComponent(mainLayout);
        addComponent(mainPanel);
        setComponentAlignment(mainPanel, Alignment.TOP_CENTER);
        setMargin(true);
    }

}
