package org.example;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Hover {
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

    @Test(priority = 6)
    public void Hover() throws InterruptedException {
//        ElementHandle element  = page.querySelector("//body/div[@id='page']/div[@class='header-container']/header[@id='header']/div/div[@class='container']/div[@class='row']/div[@id='block_top_menu']/ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a[1]");
//       element.hover();
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }


}
