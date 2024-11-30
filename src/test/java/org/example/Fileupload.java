package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class Fileupload {

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
    @Test(priority = 0)
    public void openURL() throws InterruptedException {
        page.navigate(URL);
        page.evaluate("document.body.style.zoom = '80%'");
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void inputsinglefile() throws InterruptedException {
        ElementHandle element  = page.querySelector("//input[@id='picture']");
        element.setInputFiles(Paths.get("files/final logo jamuna.png"));
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }
}
