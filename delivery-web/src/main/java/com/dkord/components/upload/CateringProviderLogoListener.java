/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.components.upload;

import com.dkord.datamodel.CateringProvider;
import com.vaadin.Application;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author Peter Gyschuk
 */
public class CateringProviderLogoListener implements SucceededListener {

    private Embedded image;

    private CateringProvider cateringProvider;

    private ByteArrayOutputStream fileByteArray;

    public CateringProviderLogoListener(Embedded image, CateringProvider cateringProvider, ByteArrayOutputStream fileByteArray) {
        this.image = image;
        this.cateringProvider = cateringProvider;
        this.fileByteArray = fileByteArray;
    }

    @Override
    public void uploadSucceeded(SucceededEvent event) {
        image.setVisible(true);
        image.setSource(new StreamResource(new ImageSource(fileByteArray.toByteArray()), "Logo.png", Application.getCurrent()));
        cateringProvider.setLogo(fileByteArray.toByteArray());
    }
}
