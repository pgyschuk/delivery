package com.dkord.pages;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter Gyschuk
 */
public class UserEditLayout extends Configuration {

    private EJBAccessLocal ejbAccess;

    private TwinColSelect roleSelect;

    private ListSelect select;

    private Panel userInfoPanel;

    private VerticalLayout userManagementLayout;

    private static Logger LOGGER = LoggerFactory.getLogger(UserEditLayout.class);

    public UserEditLayout(final EJBAccessLocal ejbAccess) {
        this.ejbAccess = ejbAccess;
        userManagementLayout = new VerticalLayout();
        userManagementLayout.setWidth("100%");
        userManagementLayout.setSpacing(true);
        HorizontalLayout rolesLayout = new HorizontalLayout();
        rolesLayout.setSpacing(true);
        userInfoPanel = new Panel();
        userInfoPanel.setWidth("100%");
        userManagementLayout.addComponent(rolesLayout);
        userManagementLayout.addComponent(userInfoPanel);
        final List<User> users = ejbAccess.getUsersService().findAll();
        final List<Role> roles = ejbAccess.getRolesService().findAll();


        select = new ListSelect("Find User");
        select.setWidth("180px");
        select.setNullSelectionAllowed(false);
        select.setImmediate(true);
        select.setContainerDataSource(new BeanItemContainer(User.class, users));
        roleSelect = new TwinColSelect();
        roleSelect.setLeftColumnCaption("All roles");
        roleSelect.setRightColumnCaption("Assigned Roles");
        roleSelect.setWidth("400px");
        for (Role role : roles) {
            roleSelect.addItem(role);
        }

        select.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Property selected = event.getProperty();
                if (selected != null) {
                    User user = (User) selected.getValue();
                    roleSelect.setValue(user.getRoles());
                    roleSelect.setImmediate(true);
                    userInfoPanel.setContent(new UserContactsLayout(ejbAccess, user));
                }
            }
        });
        final Button deleteButton = new Button("Delete");
        deleteButton.setStyleName("primary");

        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                User user = (User) select.getValue();
                if (user != null) {
                    ejbAccess.getUsersService().delete(user);
                    users.remove(user);
                    select.setContainerDataSource(new BeanItemContainer(User.class, users));
                    LOGGER.info("User {} was deleted", new Object[]{user});
                }
            }
        });
        rolesLayout.addComponent(select);
        rolesLayout.addComponent(roleSelect);
        addComponent(userManagementLayout);
        addComponent(deleteButton);
        setMargin(true);
        setSpacing(true);
    }

    @Override
    public void save() {
        User user = (User) select.getValue();
        user.setRoles((Set<Role>) roleSelect.getValue());
        ejbAccess.getUsersService().save(user);
        ((UserContactsLayout) userInfoPanel.getContent()).save();
        userManagementLayout.setImmediate(true);
    }

    @Override
    public String getName() {
        return "Edit users";
    }
}
