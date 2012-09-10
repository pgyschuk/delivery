package com.dkord.pages.admin;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.Role;
import com.dkord.datamodel.User;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TwinColSelect;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Peter Gyschuk
 */
public class RolesLayout extends HorizontalLayout implements Configuration {
    
    EJBAccessLocal ejbAccess;

    final TwinColSelect roleSelect;

    ComboBox select;

    public RolesLayout(final EJBAccessLocal ejbAccess) {
        this.ejbAccess = ejbAccess;

        List<User> users = ejbAccess.getUsersService().findAll();
        final List<Role> roles = ejbAccess.getRolesService().findAll();


        select = new ComboBox("Find User");
        select.setNullSelectionAllowed(false);
        select.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
        select.setImmediate(true);
        for (User user : users) {
            select.addItem(user);
        }
        roleSelect = new TwinColSelect("Set Role");
        roleSelect.setLeftColumnCaption("All roles");
        roleSelect.setRightColumnCaption("Assigned Roles");
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
                }
            }
        });

        addComponent(select);
        addComponent(roleSelect);
    }

    @Override
    public void save() {
        User user = (User)select.getValue();
        user.setRoles((Set<Role>)roleSelect.getValue());
        ejbAccess.getUsersService().save(user);
    }
}
