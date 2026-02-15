package com.orangehrm.automation.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class LocatorReader {

    private static JsonObject locators;

    static {
        try {
            FileReader reader =
                    new FileReader("src/test/resources/locators/locators.json");

            locators = JsonParser.parseReader(reader).getAsJsonObject();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load locators.json", e);
        }
    }

    public static String get(String page, String key) {
        return locators.getAsJsonObject(page).get(key).getAsString();
    }
}

