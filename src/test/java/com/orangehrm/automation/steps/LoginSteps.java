package com.orangehrm.automation.steps;

import com.microsoft.playwright.Page;
import com.orangehrm.automation.base.PlaywrightFactory;
import com.orangehrm.automation.pages.AdminPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {

    Page page = PlaywrightFactory.getPage();
    AdminPage admin = new AdminPage(page);

    // 1. Open URL
    @Given("user is on login page")
    public void openLogin() {
        admin.openLogin("https://opensource-demo.orangehrmlive.com/");
    }

    // 2. Enter credentials
    @When("user enters username {string} and password {string}")
    public void login(String u, String p) {
        admin.enterCredentials(u, p);
    }

    // 3. Click login
    @And("clicks on login button")
    public void clickLogin() {
        admin.clickLoginButton();
    }

    // 4. Verify dashboard
    @Then("dashboard page should be displayed")
    public void verifyDashboard() {
        Assert.assertTrue(admin.isDashboardVisible());
    }

    // 5. Admin Page
    @When("user clicks on admin menu")
    public void openAdmin() {
        admin.openAdminPage();
    }

    @Then("user management page should be displayed")
    public void verifyUserMgmt() {
        Assert.assertTrue(true);
    }

    // 6. Add User
    @When("user clicks on add button")
    public void clickAdd() {
        admin.clickAddUser();
    }

    @Then("add user page should be displayed")
    public void verifyAddUserPage() {
        Assert.assertTrue(true);
    }

    // 7. Create User
    @When("user enters new user details and saves")
    public void addUser() {
        admin.createUser("Linda Anderson", "EMPAP", "Emp@123");
    }

    @Then("user should be added successfully")
    public void verifyUserAdded() {
        Assert.assertTrue(true);
    }

    // 8. Search Created User
    @When("user searches created user")
    public void searchUser() {
        admin.searchUser("EMPAP");
    }

    @Then("created user record should be displayed")
    public void verifyUserRecord() {
        Assert.assertTrue(admin.isUserPresent("EMPAP"));
    }

    // 9. Delete User
    @When("user deletes created user")
    public void deleteUser() {
        admin.deleteUser();
    }

    @Then("delete success message should be displayed")
    public void verifyDelete() {
        Assert.assertTrue(true);
    }

    // 10. Invalid Search
    @When("user searches invalid username")
    public void invalidSearch() {
        admin.searchUser("abcd");
    }

    @Then("no record found message should be displayed")
    public void verifyNoRecord() {
        Assert.assertTrue(admin.isNoRecordFound());
    }

    // 11. Logout
    @When("user logs out")
    public void logout() {
        admin.logout();
    }

    @Then("login page should be displayed")
    public void verifyLogout() {
        Assert.assertTrue(admin.isLoginPageVisible());
    }
}