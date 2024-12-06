package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RetrieveData {
    String URL = "https://www.tutorialspoint.com/selenium/practice/register.php";
    Playwright playwright;
    BrowserType browserType;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeSuite
    public void startBrowser(){


        playwright = Playwright.create();
        browserType = playwright.firefox();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions());
        page = browser.newPage();

    }

    @Test(priority = 0)
    public void openURL() throws InterruptedException {
        page.navigate(URL);
        Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void findattribute() throws InterruptedException {
//        page.setDefaultTimeout(60000);
        Locator firstname = page.locator("//input[@id='firstname']");
        System.out.println(firstname.getAttribute("placeholder"));

    }


    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }

}
