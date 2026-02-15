package com.orangehrm.automation.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ActionUtils {

    public static void fill(Page page, String locator, String value) {
        try {
            page.fill(locator, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fill field: " + locator, e);
        }
    }

    public static void click(Page page, String locator) {
        try {
            page.waitForSelector(locator,
                    new Page.WaitForSelectorOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(30000));

            page.locator(locator).scrollIntoViewIfNeeded();
            page.locator(locator).click(new Locator.ClickOptions().setTimeout(30000));

        } catch (Exception e) {
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }
}