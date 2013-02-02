package com.dkord.components;

import com.dkord.components.upload.CateringProviderLogoListener;
import com.dkord.components.upload.ImageSource;
import com.dkord.components.upload.FileUploader;
import com.dkord.datamodel.CateringProvider;
import com.dkord.datamodel.Contacts;
import com.vaadin.Application;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Peter Gyschuk
 */
public class CateringProviderEditComponent extends HorizontalLayout {
    
    private TextField cateringProviderName;
    
    private CateringProvider cateringProvider;
    
    private Set<ContactsEditComponent> contactsEditComponents;
    
    public CateringProviderEditComponent(CateringProvider cateringProvider) {
        this.cateringProvider = cateringProvider;
        setSpacing(true);
        setWidth("100%");
        contactsEditComponents = new HashSet<ContactsEditComponent>();
        List<Contacts> cateringProviderContactsList = cateringProvider.getContactsLocation();
        
        if (cateringProviderContactsList == null) {
            Contacts contacts = new Contacts();
            cateringProviderContactsList = new ArrayList<Contacts>();
            cateringProviderContactsList.add(contacts);
            cateringProvider.setContactsLocation(cateringProviderContactsList);
            contactsEditComponents.add(new ContactsEditComponent(contacts));
        } else {
            for (Contacts contacts : cateringProviderContactsList) {
                contactsEditComponents.add(new ContactsEditComponent(contacts));
            }
        }
        
        VerticalLayout verticalLayout = new VerticalLayout();
        
        cateringProviderName = new TextField("Catering Provider Name", cateringProvider.getName());
        //cateringProviderName.addValidator(new StringLengthValidator("Name mast be 3-50 characters", 3, 50, false));
        cateringProviderName.setWidth("100%");

        Embedded imageLogo = new Embedded("Uploaded Image");
        boolean hasLogo = cateringProvider.getLogo() != null;
        imageLogo.setVisible(hasLogo);
        if (hasLogo) {
            imageLogo.setSource(new StreamResource(new ImageSource(cateringProvider.getLogo()), "Logo.png", Application.getCurrent()));
        }
        imageLogo.setWidth("100%");
        
        FileUploader uploader = new FileUploader();
        CateringProviderLogoListener cateringProviderLogoListener = new CateringProviderLogoListener(imageLogo, cateringProvider, uploader.getFileByteArray());
        Upload uploadLogo = new Upload("Upload the file here", uploader);
        uploadLogo.setWidth("100%");
        uploadLogo.addListener(cateringProviderLogoListener);
        uploadLogo.setButtonCaption("Start Upload");
        
        Panel panelLogo = new Panel("Logo");
        panelLogo.addComponent(uploadLogo);
        panelLogo.addComponent(imageLogo);
        
        verticalLayout.addComponent(cateringProviderName);
        verticalLayout.addComponent(panelLogo);
        addComponent(verticalLayout);
        
        for (ContactsEditComponent contactsEditComponent : contactsEditComponents) {
            addComponent(contactsEditComponent);
        }
    }
    
    public CateringProvider getCateringProvider() {
        List<Contacts> cateringProviderContactsList = new ArrayList<Contacts>();
        for (ContactsEditComponent contactsEditComponent : contactsEditComponents) {
            cateringProviderContactsList.add(contactsEditComponent.getContacts());
        }
        cateringProvider.setContactsLocation(cateringProviderContactsList);
        cateringProvider.setName(cateringProviderName.getValue());
        return cateringProvider;
    }
}
