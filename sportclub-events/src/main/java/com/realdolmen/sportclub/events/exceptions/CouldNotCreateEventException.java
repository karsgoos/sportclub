package com.realdolmen.sportclub.events.exceptions;

public class CouldNotCreateEventException extends Exception {
    public CouldNotCreateEventException(Exception cause) {
        this(cause.getMessage());
    }

    public CouldNotCreateEventException() {

    }

    public CouldNotCreateEventException(String s) {
        super(s);
    }
}
