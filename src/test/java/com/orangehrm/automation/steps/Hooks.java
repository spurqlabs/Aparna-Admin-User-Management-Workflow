package com.orangehrm.automation.steps;

import com.orangehrm.automation.base.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        // Start browser before scenario
        PlaywrightFactory.initBrowser();
    }

    @After
    public void tearDown() {
        // Close browser after scenario
        PlaywrightFactory.closeBrowser();
    }
}