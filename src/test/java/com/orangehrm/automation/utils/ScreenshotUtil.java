package com.orangehrm.automation.utils;

import com.microsoft.playwright.Page;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void capture(Page page, String name) {
        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/" + name + ".png"))
                    .setFullPage(true));
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}
