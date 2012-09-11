package com.dkord.components;

import com.dkord.datamodel.Contacts;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import java.util.Set;

/**
 *
 * @author Peter Gyschuk
 */
public class ContactsListEditComponent extends VerticalLayout {

    private ContactsEditComponent contactsEditComponent = new ContactsEditComponent(new Contacts());

    public ContactsListEditComponent(Set<Contacts> contactsList) {
        ComboBox select = new ComboBox("Select Contacts");
        select.setNullSelectionAllowed(false);
        select.setFilteringMode(AbstractSelect.Filtering.FILTERINGMODE_CONTAINS);
        select.setImmediate(true);
        for (Contacts contacts : contactsList) {
            select.addItem(contacts);
        }
        select.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Property selected = event.getProperty();
                if (selected != null) {
                    contactsEditComponent = new ContactsEditComponent((Contacts) selected.getValue());
                }
            }
        });
        
        addComponent(select);
        addComponent(contactsEditComponent);
    }
}
