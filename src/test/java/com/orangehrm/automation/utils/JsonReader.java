package com.orangehrm.automation.utils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class JsonReader {

    private static JsonObject config;

    static {
        try {
            FileReader reader = new FileReader("src/test/resources/config/config.json");
            config = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.json", e);
        }
    }

    public static String getBaseUrl() {
        return config.get("baseUrl").getAsString();
    }

    public static String getBrowser() {
        return config.get("browser").getAsString();
    }

    public static boolean isHeadless() {
        return config.get("headless").getAsBoolean();
    }

    public static int getTimeout() {
        return config.get("timeout").getAsInt();
    }
}

