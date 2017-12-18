package com.realdolmen.sportclub.events.exceptions;

public class AttachmentNotFoundException extends Exception {

    public AttachmentNotFoundException(Exception e) {
        super(e.getMessage());
    }

    public AttachmentNotFoundException() {

    }
}
