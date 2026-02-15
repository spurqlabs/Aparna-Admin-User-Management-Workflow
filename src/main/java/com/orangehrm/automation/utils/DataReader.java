package com.orangehrm.automation.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class DataReader {

    private static JsonObject data;

    static {
        try {
            FileReader reader = new FileReader("src/test/resources/testdata/adminUser.json");
            data = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data JSON", e);
        }
    }

    public static String get(String section, String key) {
        return data.getAsJsonObject(section).get(key).getAsString();
    }
}
