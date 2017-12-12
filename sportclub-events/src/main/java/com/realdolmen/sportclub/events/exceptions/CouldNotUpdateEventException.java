package com.realdolmen.sportclub.events.exceptions;

public class CouldNotUpdateEventException extends Throwable {
    public CouldNotUpdateEventException(Exception e) {
        super(e);
    }

    public CouldNotUpdateEventException() {

    }

    public CouldNotUpdateEventException(String s) {
        super(s);
    }
}
