package com.orangehrm.automation.steps;

import com.microsoft.playwright.Page;
import com.orangehrm.automation.base.PlaywrightFactory;
import com.orangehrm.automation.pages.AdminPage;
import com.orangehrm.automation.utils.DataReader;
import io.cucumber.java.en.*;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginSteps {

    private static final Logger log = LogManager.getLogger(LoginSteps.class);

    Page page = PlaywrightFactory.getPage();
    AdminPage admin = new AdminPage(page);

    private String username;

    @Given("user is on login page")
    public void openLogin() {
        // URL handled in hooks/config
    }

    @When("user enters username {string} and password {string}")
    public void login(String u, String p) {
        log.info("Entering credentials");
        admin.enterCredentials(u, p);
    }

    @And("clicks on login button")
    public void clickLogin() {
        admin.clickLoginButton();
    }

    @Then("dashboard page should be displayed")
    public void verifyDashboard() {
        Assert.assertTrue(admin.isDashboardVisible(), "Dashboard not visible");
    }

    @When("user clicks on admin menu")
    public void openAdmin() {
        admin.openAdminPage();
    }

    @Then("user management page should be displayed")
    public void verifyUserMgmt() {
        Assert.assertTrue(admin.isUserMgmtVisible(), "User Management page not visible");
    }

    @When("user clicks on add button")
    public void clickAdd() {
        admin.clickAddUser();
    }

    @Then("add user page should be displayed")
    public void verifyAddUserPage() {
        Assert.assertTrue(admin.isAddUserPageVisible(), "Add User page not visible");
    }

    @When("user enters new user details and saves")
    public void addUser() {
        username = "EMP" + System.currentTimeMillis();

        admin.createUser(
                DataReader.get("newUser", "employeeName"),
                username,
                DataReader.get("newUser", "password")
        );
    }

    @Then("user should be added successfully")
    public void verifyUserAdded() {
        Assert.assertTrue(admin.isUserPresent(username), "User not created");
    }

    @When("user searches created user")
    public void searchUser() {
        admin.searchUser(username);
    }

    @Then("created user record should be displayed")
    public void verifyUserRecord() {
        Assert.assertTrue(admin.isUserPresent(username), "Created user not found");
    }

    @When("user deletes created user")
    public void deleteUser() {
        admin.deleteUser();
    }

    @Then("delete success message should be displayed")
    public void verifyDelete() {
        Assert.assertTrue(admin.isNoRecordFound() || !admin.isUserPresent(username),
                "User deletion failed");
    }

    @When("user searches invalid username")
    public void invalidSearch() {
        admin.searchUser(DataReader.get("invalidSearch", "username"));
    }

    @Then("no record found message should be displayed")
    public void verifyNoRecord() {
        Assert.assertTrue(admin.isNoRecordFound(), "No record message not displayed");
    }
}