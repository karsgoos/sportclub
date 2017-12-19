package com.realdolmen.sportclub.events.exceptions;

public class CouldNotUpdateEventException extends Exception {
    public CouldNotUpdateEventException(Exception e) {
        this(e.getMessage());
    }

    public CouldNotUpdateEventException() {

    }

    public CouldNotUpdateEventException(String s) {
        super(s);
    }
}
