package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorPractice extends BrowserInit{

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

        @Test(priority = 0)
        public void openURL() throws InterruptedException {
            page.navigate(URL);
            page.evaluate("document.body.style.zoom = '80%'");
            Thread.sleep(5000);
        }
        @Test(priority = 1)
        public void locatedbyID(){
            ElementHandle element = page.querySelector("#name");
            element.fill("Akram khan");
        }
        @Test(priority = 2)
        public void locatebyName(){
            ElementHandle element = page.querySelector("[name='mobile']");
            element.fill("01521438849");
        }
        @Test(priority = 3)
        public void locatebyxpath(){
            ElementHandle element = page.querySelector("//input[@id='subjects']");
            element.fill("Science");
        }
        @Test(priority = 4)
        public void selectByIndex() throws InterruptedException {
            ElementHandle element = page.querySelector("//select[@id='state']");
            element.selectOption(new SelectOption().setIndex(1));
            Thread.sleep(5000);
        }

        @Test(priority = 5)
        public void selectByValue() throws InterruptedException {
            ElementHandle element = page.querySelector("//select[@id='state']");
            element.selectOption(new SelectOption().setValue("Haryana"));
            Thread.sleep(5000);
        }
        @Test(priority = 5)
        public void selectByVisibletext() throws InterruptedException {
            ElementHandle element = page.querySelector("//select[@id='state']");
            element.selectOption(new SelectOption().setLabel("Rajasthan"));
            Thread.sleep(5000);
        }

        @AfterSuite
        public void closeBrowser(){
            page.close();
            browser.close();
            playwright.close();
        }


    }

}
