package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SwitchingWindowTab {
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
    public void SwitchToSecondWindow() throws InterruptedException {
        Page secondwindow = browser.newContext().newPage();
        secondwindow.bringToFront();
        secondwindow.navigate("https://www.tutorialspoint.com/selenium/practice/new-window.php");
        Thread.sleep(3000);
        secondwindow.close();

        //bring first window  to the front
        page.bringToFront();
        Thread.sleep(3000);


    }


    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }

}
