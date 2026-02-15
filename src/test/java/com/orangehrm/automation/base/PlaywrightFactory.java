package com.orangehrm.automation.base;

import com.microsoft.playwright.*;
import com.orangehrm.automation.utils.ConfigReader;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page initBrowser() {

        String browserName = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless));
                break;

            case "webkit":
                browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(headless));
                break;

            default:
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setSlowMo(1200)   // ← slows every action by 800 ms
                );
//                browser = playwright.chromium().launch(
//                        new BrowserType.LaunchOptions().setHeadless(headless));
        }

        context = browser.newContext();
        page = context.newPage();

        page.navigate(ConfigReader.get("base.url"),new Page.NavigateOptions().setTimeout(60000));

        return page;
    }

    public static Page getPage() {
        return page;
    }

    public static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}