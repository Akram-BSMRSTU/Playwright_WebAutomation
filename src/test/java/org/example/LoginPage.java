package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPage {

    String URL = "https://practicetestautomation.com/practice-test-login/";
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeSuite
    public void startBrowser() {
        playwright = Playwright.create();
        browserType = playwright.firefox();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @Test(priority = 0)
    public void openURL() {
        page.navigate(URL);
    }

    @Test(priority = 1, dataProvider = "LoginDataProvider")
    public void login(String username, String password) {
        // Locate elements
        Locator Username = page.locator("//input[@name='username']");
        Locator Password = page.locator("//input[@name='password']");
        Locator LoginButton = page.locator("//button[@id='submit']");
        Locator SuccessMessage = page.locator("//h1[normalize-space()='Logged In Successfully']");
        Locator ErrorMessage = page.locator("//div[@id='error']");
        Locator LogoutButton = page.locator("//a[normalize-space()='Log out']");

        // Fill login form and click login
        Username.fill(username);
        Password.fill(password);
        LoginButton.click();

        // Wait for either success or error message
        page.waitForTimeout(2000); // Adjust timeout based on your application's response time

        if (SuccessMessage.isVisible()) {
            // Assert success message and log out
//            assert SuccessMessage.isVisible() : "Success message not visible for user: " + username;
            System.out.println("Login successful for user: " + username);
            LogoutButton.click();
        } else if (ErrorMessage.isVisible()) {
            // Assert error message and log the failed username
//            assert ErrorMessage.isVisible() : "Error message not visible for invalid login: " + username;
            System.out.println("Login failed for user: " + username);
        } else {
            // Handle unexpected behavior
            System.out.println("Unexpected state for user: " + username);
        }
    }

    @DataProvider(name = "LoginDataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {"student", "Password123"}, // Valid credentials
                {"invalidUser", "Password143"}, // Invalid credentials
                {"testUser", "wrongPassword123"} // Invalid credentials
        };
    }

    @AfterSuite
    public void closeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
