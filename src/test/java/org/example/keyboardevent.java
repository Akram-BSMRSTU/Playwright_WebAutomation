package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class keyboardevent {
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
    public void keyboardtest() throws InterruptedException {
//        page.setDefaultTimeout(60000);
        Locator firstname = page.locator("//input[@id='firstname']");
        firstname.click();
        page.keyboard().type("akram khan");
//      select ALL
        page.keyboard().down("Control");
        page.keyboard().press("KeyA");
        page.keyboard().up("Control");
//        copy all
        page.keyboard().down("Control");
        page.keyboard().press("KeyC");
        page.keyboard().up("Control");
        Thread.sleep(5000);


        page.locator("//input[@id='username']").click();


        page.keyboard().down("Control");
        page.keyboard().press("KeyV");
        page.keyboard().up("Control");
        Thread.sleep(5000);

    }


    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }

}
