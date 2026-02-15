package com.orangehrm.automation.Hooks;

import com.microsoft.playwright.Page;
import com.orangehrm.automation.base.PlaywrightFactory;
import com.orangehrm.automation.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    Page page;

    @Before
    public void setUp() {
        page = PlaywrightFactory.initBrowser();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtil.capture(page, scenario.getName());
        }

        PlaywrightFactory.closeBrowser();
    }
}