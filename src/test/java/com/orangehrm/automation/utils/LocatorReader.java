package com.orangehrm.automation.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class LocatorReader {

    private static JsonObject locators;

    static {
        try {
            FileReader reader = new FileReader("src/test/resources/locators/locators.json");
            locators = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Unable to load locators.json", e);
        }
    }

    public static String get(String page, String key) {
        try {
            return locators.getAsJsonObject(page).get(key).getAsString();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Locator not found → Page: " + page + " | Key: " + key, e);
        }
    }
}
