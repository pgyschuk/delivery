package com.dkord.pages.admin;

import com.dkord.EJBAccessLocal;
import com.dkord.pages.security.LoginLayout;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Peter Gyschuk
 */
public class AdminLayout extends VerticalLayout implements View {

    public AdminLayout(final EJBAccessLocal ejbAccess) {
        super();
        final Panel adminPanel = new Panel();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        adminPanel.setCaption("Admin panel");
        adminPanel.setWidth("900px");
        Panel menuPanel = new Panel();
        menuPanel.setCaption("Menu");
        menuPanel.setWidth("200px");
        final Panel contentPanel = new Panel();
        contentPanel.setCaption("Options");
        contentPanel.setWidth("600px");
        horizontalLayout.addComponent(menuPanel);
        horizontalLayout.addComponent(contentPanel);


        VerticalLayout userAction = new VerticalLayout();

        Button assignRoleButton = new Button("Assign role");
        assignRoleButton.addStyleName("link");
        assignRoleButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                contentPanel.setContent(new LoginLayout(ejbAccess));
            }
        });
        userAction.addComponent(assignRoleButton);

        menuPanel.addComponent(userAction);
        adminPanel.addComponent(horizontalLayout);
        addComponent(adminPanel);
        setComponentAlignment(adminPanel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }

    @Override
    public void navigateTo(String fragmentParameters) {
    }
}
