package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollWindow {
    String URL = "http://www.automationpractice.pl/index.php";
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
    public void ScrollDown() throws InterruptedException {
        String script = "window.scrollTo(0, document.body.scrollHeight)";
        page.evaluate(script);
        Thread.sleep(5000);
    }
    @Test(priority = 2)
    public void ScrollUp() throws InterruptedException {
        String script = "window.scrollTo(0,0)";
        page.evaluate(script);
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    public void ScrollSpecificElement() throws InterruptedException {
        ElementHandle element = page.querySelector("//h4[normalize-space()='Follow us on Facebook']");
        element.scrollIntoViewIfNeeded();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }
}
