package com.realdolmen.sportclub.events.exceptions;

public class AttachmentNotFoundException extends Exception {

    public AttachmentNotFoundException(IllegalArgumentException e) {
        super(e);
    }

    public AttachmentNotFoundException() {

    }
}
