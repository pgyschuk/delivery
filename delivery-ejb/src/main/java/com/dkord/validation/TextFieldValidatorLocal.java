package com.dkord.validation;

import com.vaadin.ui.ComponentContainer;
import javax.ejb.Local;

/**
 *
 * @author Peter Gyschuk
 */
@Local
public interface TextFieldValidatorLocal {

    boolean isValid(ComponentContainer container);
}
