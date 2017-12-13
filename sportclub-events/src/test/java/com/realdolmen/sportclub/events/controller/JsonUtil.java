package com.realdolmen.sportclub.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static String asJsonString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
