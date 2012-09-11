package com.dkord.components;

import com.dkord.datamodel.Contacts;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Peter Gyschuk
 */
public class ContactsEditComponent extends VerticalLayout {

    private Contacts contacts;

    private TextField city;

    private TextField email;

    private TextField number;

    private TextField street;

    private TextField telephone;

    public ContactsEditComponent(Contacts contacts) {
        this.contacts = contacts;

        city = new TextField("City", contacts.getCity());
        city.setWidth("100%");

        email = new TextField("Email", contacts.getEmail());
        email.addValidator(new EmailValidator("Email is not valid"));
        email.setValidationVisible(false);
        email.setWidth("100%");

        number = new TextField("Number", contacts.getNumber());
        number.setWidth("100%");

        street = new TextField("Street", contacts.getStreet());
        street.setWidth("100%");

        telephone = new TextField("Telephone", contacts.getTelephone());
        telephone.setWidth("100%");

        Button addNewContactsButton = new Button("Add new Contacts");
        addNewContactsButton.addStyleName("link");
        addNewContactsButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                setContacts(new Contacts());
                city.setValue("");
                email.setValue("");
                number.setValue("");
                street.setValue("");
                telephone.setValue("");
            }
        });

        addComponent(city);
        addComponent(email);
        addComponent(number);
        addComponent(street);
        addComponent(telephone);
        addComponent(addNewContactsButton);
    }

    public Contacts getContacts() {
        contacts.setCity(city.getValue());
        contacts.setEmail(email.getValue());
        contacts.setNumber(number.getValue());
        contacts.setStreet(street.getValue());
        contacts.setTelephone(telephone.getValue());
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
}
