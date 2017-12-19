package com.realdolmen.sportclub.common.dto;


// Use this to return json messages in controllers
public class MessageDto {

    //this is the standard response object for API calls. if error is empty, the call succeeded, and the response will be in the value field
    private String error;

    //this field is where the response will be if no errors.
    private Object value;

    public MessageDto(String error, Object value) {
        this.error = error;
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
