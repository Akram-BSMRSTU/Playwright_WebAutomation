package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class iframe {
    String URL = "https://www.tutorialspoint.com/selenium/practice/frames.php";
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
    public void Handleiframe() throws InterruptedException {
//        page.setDefaultTimeout(60000);
        page.frame("frame1");
        Locator firstname = page.locator("//h1[normalize-space()='Selenium - Automation Practice Form']");
        System.out.println("Content from iframe"+firstname.textContent());
//        TO select anything outside iframe
        page.mainFrame();

        Locator text = page.locator("//h1[normalize-space()='Frames']");
        System.out.println("text from outside iframe "+text.textContent());
    }


    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }

}
