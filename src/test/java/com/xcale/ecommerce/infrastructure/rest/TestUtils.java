package com.xcale.ecommerce.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtils {
    private static final ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }
    public static <T> T readDataFromFile(String fileName, Class<T> className) throws IOException {
        return mapper.readValue(
                TestUtils.class.getClassLoader().getResourceAsStream(fileName),className);

    }
}
