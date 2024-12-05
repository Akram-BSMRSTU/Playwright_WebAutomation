package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class alert {

    String URL = "https://www.tutorialspoint.com/selenium/practice/alerts.php";
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
    public void ManageAlert(String xpath,String condition) throws InterruptedException {
//        click the locator
        Locator alertButton = page.locator(xpath);
        alertButton.click();

        page.onceDialog(dialog -> {
            System.out.println("Dialog Messsage"+dialog.message());
            System.out.println("Dialog type"+dialog.type());

            if (dialog.type().equals("alert")){
                dialog.accept();
            }else if (dialog.type().equals("confirm")){
                if (condition.equals("accept")){
                    dialog.accept();
                } else if (condition.equals("reject")) {
                    dialog.dismiss();

                }
            } else if (dialog.type().equals("prompt")){
                if (condition.equals("accept")){
                    dialog.accept("Accepting this prompt");
                } else if (condition.equals("reject")) {
                    dialog.dismiss();
                }
        }
        });
        alert alertHandler = new alert();
        alertHandler.ManageAlert("//button[@onclick='myDesk()']", "accept");
    }

    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }
}
