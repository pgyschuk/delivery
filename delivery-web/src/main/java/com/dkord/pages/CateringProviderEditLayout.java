package com.dkord.pages;

import com.dkord.EJBAccessLocal;
import com.dkord.datamodel.CateringProvider;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter Gyschuk
 */
public class CateringProviderEditLayout extends Configuration {

    private ListSelect cateringProviderSelect;

    private Panel cateringProviderInfoPanel;

    private VerticalLayout cateringProviderManagementLayout;

    private HorizontalLayout buttonBar;

    private static Logger LOGGER = LoggerFactory.getLogger(CateringProviderEditLayout.class);
    
    final List<CateringProvider> cateringProviders;

    public CateringProviderEditLayout(final EJBAccessLocal ejbAccess) {

        cateringProviders = ejbAccess.getCateringProviderService().findAll();
        buttonBar = new HorizontalLayout();
        cateringProviderManagementLayout = new VerticalLayout();
        cateringProviderManagementLayout.setWidth("100%");
        cateringProviderManagementLayout.setSpacing(true);
        cateringProviderInfoPanel = new Panel();
        cateringProviderInfoPanel.setWidth("100%");

        cateringProviderSelect = new ListSelect("Catering Provider");
        cateringProviderSelect.setWidth("100%");
        cateringProviderSelect.setImmediate(true);
        cateringProviderSelect.setNullSelectionAllowed(false);
        cateringProviderSelect.setContainerDataSource(new BeanItemContainer(CateringProvider.class, cateringProviders));

        cateringProviderSelect.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Property selected = event.getProperty();
                if (selected != null) {
                    CateringProvider cateringProvider = (CateringProvider) selected.getValue();
                    if (cateringProvider != null) {
                        cateringProviderInfoPanel.setContent(new CateringProviderContactsLayout(ejbAccess, cateringProvider));
                    } else {
                         cateringProviderInfoPanel.removeAllComponents();
                    }
                }
            }
        });

        final Button deleteButton = new Button("Delete");
        deleteButton.setStyleName("primary");
        deleteButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CateringProvider cateringProvider = (CateringProvider) cateringProviderSelect.getValue();
                if (cateringProvider != null) {
                    ejbAccess.getCateringProviderService().delete(cateringProvider);
                    cateringProviders.remove(cateringProvider);
                    cateringProviderSelect.setContainerDataSource(new BeanItemContainer(CateringProvider.class, cateringProviders));
                    LOGGER.info("CateringProvider {} was deleted", new Object[]{cateringProvider});
                }
            }
        });

        final Button addButton = new Button("Add");
        addButton.setStyleName("primary");
        addButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CateringProvider cateringProvider = new CateringProvider();
                cateringProviders.add(cateringProvider);
                cateringProviderSelect.setContainerDataSource(new BeanItemContainer(CateringProvider.class, cateringProviders));
                cateringProviderSelect.select(cateringProvider);
                cateringProviderInfoPanel.setContent(new CateringProviderContactsLayout(ejbAccess, cateringProvider));
            }
        });

        cateringProviderManagementLayout.addComponent(cateringProviderSelect);
        cateringProviderManagementLayout.addComponent(cateringProviderInfoPanel);
        addComponent(cateringProviderManagementLayout);
        buttonBar.setSpacing(true);
        buttonBar.addComponent(addButton);
        buttonBar.addComponent(deleteButton);
        addComponent(buttonBar);
        setMargin(true);
        setSpacing(true);
    }

    @Override
    public void save() {
        ((CateringProviderContactsLayout) cateringProviderInfoPanel.getContent()).save();
        cateringProviderManagementLayout.setImmediate(true);
        cateringProviderSelect.setContainerDataSource(new BeanItemContainer(CateringProvider.class, cateringProviders));
    }

    @Override
    public String getName() {
        return "Edit Catering Providers";
    }
}
