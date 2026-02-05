package com.orangehrm.automation.pages;

import com.microsoft.playwright.Page;

public class AdminPage {

    private Page page;

    public AdminPage(Page page) {
        this.page = page;
    }

    // ================= LOGIN =================
    private String usernameField = "input[name='username']";
    private String passwordField = "input[name='password']";
    private String loginButton = "button[type='submit']";
    private String dashboardHeader = "//h6[text()='Dashboard']";

    public void openLogin(String url) {
        page.navigate(url);
    }

    // Enter credentials only
    public void enterCredentials(String u, String p) {
        page.fill(usernameField, u);
        page.fill(passwordField, p);
    }

    // Click login separately (as your feature requires)
    public void clickLoginButton() {
        page.click(loginButton);
        page.waitForSelector(dashboardHeader);
    }

    public boolean isDashboardVisible() {
        return page.locator(dashboardHeader).isVisible();
    }

    // ================= ADMIN PAGE =================
    private String adminMenu = "//span[text()='Admin']";
    private String userMgmtHeader = "//h5[text()='System Users']";
    private String addButton = "//button[normalize-space()='Add']";

    public void openAdminPage() {
        page.click(adminMenu);
        page.waitForSelector(userMgmtHeader);
    }

    public void clickAddUser() {

        page.click(addButton);
        page.waitForSelector("//h6[text()='Add User']");
        page.waitForTimeout(1500);
    }

    // ================= ADD USER =================
    private String userRoleDropdown = "(//div[contains(@class,'oxd-select-text')])[1]";
    private String userRoleAdmin = "//span[text()='Admin']";

    private String employeeNameInput = "//input[@placeholder='Type for hints...']";
    private String employeeSuggestion = "//div[@role='option']";

    private String statusDropdown   = "(//div[contains(@class,'oxd-select-text')])[2]";
    private String statusEnabled = "//span[text()='Enabled']";

    private String newUsernameInput = "(//input[@class='oxd-input oxd-input--active'])[2]";
    private String newPasswordInput = "(//input[@type='password'])[1]";
    private String confirmPasswordInput = "(//input[@type='password'])[2]";

    private String saveButton = "//button[normalize-space()='Save']";

    public void createUser(String employeeName, String username, String password) {
        page.waitForSelector(userRoleDropdown);
        page.click(userRoleDropdown);
        page.click(userRoleAdmin);

        page.fill(employeeNameInput, employeeName);
        page.waitForSelector(employeeSuggestion);
        page.click(employeeSuggestion);

        page.click(statusDropdown);
        page.click(statusEnabled);

        page.fill(newUsernameInput, username);
        page.fill(newPasswordInput, password);
        page.fill(confirmPasswordInput, password);

        page.click(saveButton);

        // wait until redirected back to admin page
        page.waitForSelector(userMgmtHeader);
    }

    // ================= SEARCH USER =================
    private String searchUsername = "(//input[@class='oxd-input oxd-input--active'])[2]";
    private String searchUserRoleDropdown = "(//div[contains(@class,'oxd-select-text')])[1]";
    private String searchStatusDropdown   = "(//div[contains(@class,'oxd-select-text')])[2]";
    private String searchButton = "//button[normalize-space()='Search']";

    private String resultTable = "//div[@class='oxd-table-body']";
    private String noRecordMsg = "//span[text()='No Records Found']";

    public void searchUser(String username) {

        page.fill(searchUsername, username);

        page.click(searchUserRoleDropdown);
        page.click(userRoleAdmin);

        page.click(searchStatusDropdown);
        page.click(statusEnabled);

        page.click(searchButton);
        page.waitForSelector(resultTable);
    }

    public boolean isUserPresent(String username) {
        return page.locator("//div[text()='" + username + "']").isVisible();
    }

    public boolean isNoRecordFound() {
        return page.locator(noRecordMsg).isVisible();
    }

    // ================= DELETE USER =================
    private String deleteButton = "//i[@class='oxd-icon bi-trash']";
    private String confirmDelete = "//button[normalize-space()='Yes, Delete']";

    public void deleteUser() {
        page.click(deleteButton);
        page.click(confirmDelete);
        page.waitForSelector(userMgmtHeader);
    }

    // ================= LOGOUT =================
    private String profileMenu = "//p[@class='oxd-userdropdown-name']";
    private String logoutBtn = "//a[text()='Logout']";
    private String loginPageVisible = "button[type='submit']";

    public void logout() {
        page.click(profileMenu);
        page.click(logoutBtn);
    }

    public boolean isLoginPageVisible() {
        return page.locator(loginPageVisible).isVisible();
    }
}