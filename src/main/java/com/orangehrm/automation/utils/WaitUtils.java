package com.orangehrm.automation.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WaitUtils {

    public static void waitForVisible(Page page, String locator) {
        page.locator(locator).waitFor();
    }

    public static void waitForClick(Page page, String locator) {
        page.locator(locator).waitFor();
        page.click(locator);
    }
    public static void waitForPageLoad(Page page) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}