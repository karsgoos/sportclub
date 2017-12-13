package com.realdolmen.sportclub.events.exceptions;

public class EventNotFoundException extends Exception {

    public EventNotFoundException(IllegalArgumentException e) {
        super(e);
    }

    public EventNotFoundException() {

    }
}
