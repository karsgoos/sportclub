package com.realdolmen.sportclub.events.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonUtil {
    public static String asJsonString(Object object){
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
           return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
