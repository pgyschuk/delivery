/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkord.components.upload;

import com.vaadin.ui.Upload.Receiver;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Peter Gyschuk
 */
public class FileUploader implements Receiver{

    ByteArrayOutputStream fileByteArray;

    public FileUploader() {
        this.fileByteArray = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream receiveUpload(String filename,
            String mimeType) {
        return fileByteArray; 
    }

    public ByteArrayOutputStream getFileByteArray() {
        return fileByteArray;
    }
    
}