package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.google.gson.internal.bind.TypeAdapters.URL;

public class BrowserInit {
    String URL = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
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

    @Test
    public void openURL() throws InterruptedException {
        page.navigate(URL);
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }


}
