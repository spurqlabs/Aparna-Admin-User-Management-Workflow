package com.orangehrm.automation.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Locators
    private String usernameInput = "input[name='username']";
    private String passwordInput = "input[name='password']";
    private String loginButton = "button[type='submit']";
    private String dashboardHeader = "//h6[text()='Dashboard']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigateToLoginPage() {
        page.navigate("https://opensource-demo.orangehrmlive.com/");
    }

    public void enterUsername(String username) {
        page.fill(usernameInput, username);
    }

    public void enterPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLogin() {
        page.click(loginButton);
        page.waitForLoadState();
    }

    public boolean isDashboardDisplayed() {
        return page.locator(dashboardHeader).isVisible();
    }

    public String getPageTitle() {
        return page.title();
    }
}