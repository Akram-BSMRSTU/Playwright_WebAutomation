package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class HandlingTable {
    String URL = "https://demo.guru99.com/test/web-table-element.php";
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
//    @Test(priority = 1)
//    public void table() throws InterruptedException {
//        List<ElementHandle> headings = page.querySelectorAll("//table[@class=\"dataTable\"]/thead/tr/th");
//        for (int i = 1; i<=headings.size(); i++)
//        {
//            ElementHandle headingsvalue = page.querySelector("//table[@class=\"dataTable\"]/thead/tr/th["+i+"]");
//            System.out.print(headingsvalue.textContent()+" ");
//        }
//        Thread.sleep(3000);
//
//    }
    @Test(priority = 1)
    public void max() throws InterruptedException, ParseException {
        //table[@class="dataTable"]/tbody/tr


        String max;
        double m=0,r=0;
        List<ElementHandle> rows = page.querySelectorAll("//table[@class=\"dataTable\"]/tbody/tr");
        for (int i=1;i<=rows.size();i++){
            max = String.valueOf(page.querySelector("//table[@class=\"dataTable\"]/tbody/tr["+i+"]/td[4]"));
//            System.out.println(max.innerText());
            NumberFormat f = NumberFormat.getNumberInstance();
            Number num = f.parse(max);

            max = num.toString();
            m = Double.parseDouble(max);
        }
        Thread.sleep(3000);

    }


    @AfterSuite
    public void closeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }

}
