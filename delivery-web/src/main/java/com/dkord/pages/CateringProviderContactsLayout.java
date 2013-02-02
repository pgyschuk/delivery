package com.dkord.pages;

import com.dkord.EJBAccessLocal;
import com.dkord.components.CateringProviderEditComponent;
import com.dkord.datamodel.CateringProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class CateringProviderContactsLayout extends Configuration {

    private EJBAccessLocal ejbAccess;

    private VerticalLayout cateringProviderInfoLayout;

    private CateringProviderEditComponent cateringProviderEditComponent;

    public CateringProviderContactsLayout(final EJBAccessLocal ejbAccess, CateringProvider cateringProvider) {
        super();
        this.ejbAccess = ejbAccess;
        cateringProviderInfoLayout = new VerticalLayout();
        cateringProviderEditComponent = new CateringProviderEditComponent(cateringProvider);

        cateringProviderInfoLayout.setWidth("100%");
        cateringProviderInfoLayout.addComponent(cateringProviderEditComponent);
        cateringProviderInfoLayout.setSpacing(true);
        addComponent(cateringProviderInfoLayout);
        setComponentAlignment(cateringProviderInfoLayout, Alignment.MIDDLE_CENTER);
        setMargin(true);
        setSpacing(true);
    }

    @Override
    public void save() {
        if (ejbAccess.getTextFieldValidator().isValid(cateringProviderInfoLayout)) {
            ejbAccess.getCateringProviderService().save(cateringProviderEditComponent.getCateringProvider());
        }
    }

    @Override
    public String getName() {
        return "Catering Provider info";
    }
}
