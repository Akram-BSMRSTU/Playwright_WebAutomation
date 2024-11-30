package org.example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Hover {
    String URL = "https://practice-automation.com/hover/";
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
        page.evaluate("document.body.style.zoom = '80%'");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void Hover() throws InterruptedException {
        ElementHandle element  = page.querySelector("//h3[@id='mouse_over']");
       element.hover();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }


}
