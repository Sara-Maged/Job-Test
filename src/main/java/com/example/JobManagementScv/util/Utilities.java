package com.example.JobManagementScv.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class Utilities {

    private static ObjectMapper mapper;

    public static <T> ResponseEntity<List<T>> handleListResponse(List<T> list) {
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public static ObjectMapper getObjectMapper() {
        if(mapper == null) {
            mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        return mapper;
    }
}
