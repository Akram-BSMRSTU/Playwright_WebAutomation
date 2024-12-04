package org.example;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Datepicker {
    String URL = "https://demo.automationtesting.in/Datepicker.html";
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
//        page.evaluate("document.body.style.zoom = '80%'");
//        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void DatepickerEnabled() throws InterruptedException {
        ElementHandle element  = page.querySelector("//input[@id='datepicker2']");
        element.fill("19/03/1999");
        page.keyboard().press("Enter");
        Thread.sleep(5000);
    }
    @Test(priority = 2)
    public void DatepickerDisabled() throws InterruptedException {
//        ElementHandle element  = page.querySelector("//input[@id='datepicker1']");
        page.evaluate("document.getElementById('datepicker1').value='12/27/1999'");
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }


}
