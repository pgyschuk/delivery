package com.dkord.pages;

import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Peter Gyschuk
 */
public abstract class Configuration extends VerticalLayout {

    public abstract void save();
    
    public abstract String getName();
}
