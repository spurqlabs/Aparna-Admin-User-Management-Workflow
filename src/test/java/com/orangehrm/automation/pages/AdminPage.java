package com.orangehrm.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.orangehrm.automation.utils.*;
import org.apache.logging.log4j.Logger;

public class AdminPage {

    private static final Logger log = LoggerUtil.getLogger(AdminPage.class);
    private Page page;

    public AdminPage(Page page) {
        this.page = page;
    }

    // ================= LOGIN =================

    public void enterCredentials(String u, String p) {
        try {
            String user = LocatorReader.get("loginPage", "usernameField");
            String pass = LocatorReader.get("loginPage", "passwordField");

            WaitUtils.waitForVisible(page, user);
            ActionUtils.fill(page, user, u);
            ActionUtils.fill(page, pass, p);

            log.info("Entered login credentials");
        } catch (Exception e) {
            log.error("Failed to enter credentials", e);
            throw new RuntimeException("Login credential entry failed");
        }
    }

    public void clickLoginButton() {
        try {
            String loginBtn = LocatorReader.get("loginPage", "loginButton");
            WaitUtils.waitForClick(page, loginBtn);
            log.info("Clicked login button");
        } catch (Exception e) {
            log.error("Login button click failed", e);
            throw new RuntimeException("Login failed");
        }
    }

    public boolean isDashboardVisible() {
        try {
            String dash = LocatorReader.get("dashboardPage", "dashboardHeader");
            WaitUtils.waitForVisible(page, dash);
            return page.locator(dash).isVisible();
        } catch (Exception e) {
            log.error("Dashboard visibility check failed", e);
            return false;
        }
    }

    // ================= ADMIN PAGE =================

    public void openAdminPage() {
        try {
            String adminMenu = LocatorReader.get("adminPage", "adminMenu");
            String header = LocatorReader.get("adminPage", "userMgmtHeader");

            WaitUtils.waitForClick(page, adminMenu);
            WaitUtils.waitForVisible(page, header);

            log.info("Opened Admin page");
        } catch (Exception e) {
            log.error("Failed to open Admin page", e);
            throw new RuntimeException("Admin page open failed");
        }
    }

    public boolean isUserMgmtVisible() {
        return page.locator(LocatorReader.get("adminPage", "userMgmtHeader")).isVisible();
    }

    public void clickAddUser() {
        try {
            String addBtn = LocatorReader.get("adminPage", "addButton");
            String addHeader = LocatorReader.get("addUserPage", "addUserHeader");

            WaitUtils.waitForClick(page, addBtn);
            WaitUtils.waitForVisible(page, addHeader);

            log.info("Clicked Add User");
        } catch (Exception e) {
            log.error("Add User click failed", e);
            throw new RuntimeException("Add User failed");
        }
    }

    public boolean isAddUserPageVisible() {
        return page.locator(LocatorReader.get("addUserPage", "addUserHeader")).isVisible();
    }

    // ================= CREATE USER =================

    public void createUser(String employeeName, String username, String password) {
        try {
            WaitUtils.waitForVisible(page, LocatorReader.get("addUserPage", "addUserHeader"));

            ActionUtils.click(page, LocatorReader.get("addUserPage", "userRoleDropdown"));
            WaitUtils.waitForVisible(page, LocatorReader.get("addUserPage", "userRoleAdmin"));
            ActionUtils.click(page, LocatorReader.get("addUserPage", "userRoleAdmin"));

            ActionUtils.fill(page, LocatorReader.get("addUserPage", "employeeNameInput"), employeeName);

            page.locator(LocatorReader.get("addUserPage", "employeeSuggestion"))
                    .first().waitFor();
            page.locator(LocatorReader.get("addUserPage", "employeeSuggestion"))
                    .first().click();

            ActionUtils.fill(page, LocatorReader.get("addUserPage", "newUsernameInput"), username);
            ActionUtils.fill(page, LocatorReader.get("addUserPage", "newPasswordInput"), password);
            ActionUtils.fill(page, LocatorReader.get("addUserPage", "confirmPasswordInput"), password);

            ActionUtils.click(page, LocatorReader.get("addUserPage", "statusDropdown"));
            WaitUtils.waitForVisible(page, LocatorReader.get("addUserPage", "statusEnabled"));
            ActionUtils.click(page, LocatorReader.get("addUserPage", "statusEnabled"));

            ActionUtils.click(page, LocatorReader.get("addUserPage", "saveButton"));

            // wait for either success toast OR refreshed user table row count
            Locator toast = page.locator("//div[contains(@class,'oxd-toast')]");

            try {
                toast.waitFor(new Locator.WaitForOptions().setTimeout(5000));
            } catch (Exception ignored) {
                // toast may disappear fast → ignore
            }

// always ensure System Users table is visible after save
            WaitUtils.waitForVisible(page, LocatorReader.get("adminPage", "tableBody"));

            log.info("User created: {}", username);
        } catch (Exception e) {
            log.error("User creation failed", e);
            throw new RuntimeException("Create user failed - Save action did not complete properly", e);
        }
    }

    public boolean isUserPresent(String username) {
        try {
            String userRow = LocatorReader.get("adminPage", "userRow").replace("{username}", username);
            return page.locator(userRow).isVisible();
        } catch (Exception e) {
            log.error("User presence check failed", e);
            return false;
        }
    }

    // ================= SEARCH =================

    public void searchUser(String username) {
        try {
            ActionUtils.fill(page, LocatorReader.get("adminPage", "searchUsername"), username);
            ActionUtils.click(page, LocatorReader.get("adminPage", "searchButton"));

            Locator table = page.locator(LocatorReader.get("adminPage", "tableBody"));
            Locator noRecord = page.locator(LocatorReader.get("adminPage", "noRecordMsg"));

            page.waitForCondition(() -> table.isVisible() || noRecord.isVisible());

            log.info("Searched user: {}", username);
        } catch (Exception e) {
            log.error("User search failed", e);
            throw new RuntimeException("Search user failed");
        }
    }

    public boolean isNoRecordFound() {
        return page.locator(LocatorReader.get("adminPage", "noRecordMsg")).isVisible();
    }

    // ================= DELETE =================

    public void deleteUser() {
        try {
            WaitUtils.waitForClick(page, LocatorReader.get("adminPage", "deleteButton"));
            WaitUtils.waitForClick(page, LocatorReader.get("adminPage", "confirmDelete"));

            log.info("User deleted successfully");
        } catch (Exception e) {
            log.error("Delete user failed", e);
            throw new RuntimeException("Delete failed");
        }
    }
}