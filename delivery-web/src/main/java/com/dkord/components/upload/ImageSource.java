/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.components.upload;

import com.vaadin.terminal.StreamResource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author Peter Gyschuk
 */
public class ImageSource
        implements StreamResource.StreamSource {

    private byte[] byteArray;

    public ImageSource(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(byteArray);
    }
}