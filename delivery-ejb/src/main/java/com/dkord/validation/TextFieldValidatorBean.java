package com.dkord.validation;

import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import java.util.Iterator;
import javax.ejb.Stateless;

/**
 *
 * @author Peter Gyschuk
 */
@Stateless
public class TextFieldValidatorBean implements TextFieldValidatorLocal {

    @Override
    public boolean isValid(ComponentContainer container) {
        Iterator<Component> iteratorComponent = container.iterator();
        boolean isValid = true;
        while (iteratorComponent.hasNext()) {
            Component component = iteratorComponent.next();
            if (component instanceof AbstractTextField) {
                AbstractTextField textField = ((AbstractTextField) component);
                try {
                    textField.validate();
                    textField.setValidationVisible(false);
                } catch (Exception e) {
                    textField.setValidationVisible(true);
                    textField.requestRepaint();
                    isValid = false;
                }
            } else {
                continue;
            }

        }
        return isValid;
    }
}
