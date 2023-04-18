package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Assert;
import org.junit.Test;

public class OtherLocatorStrategy {


    @Test
    public void otherLocatorTest(){
       Page page=  Playwright
                .create()
                .chromium().
                launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext()
                .newPage();
       page.navigate("https://letcode.in/test");
       page.locator("'Click'").click();
      // page.locator("text=Click").click();
       Assert.assertTrue(page.url().equalsIgnoreCase("https://letcode.in/buttons") );
       page.locator("button:has-text('Home')").first().click();
       page.locator("nav :text('Product')").click();
       System.out.println(
               page.locator("//div[@class='buttons']//a").locator("nth=0").textContent());
        System.out.println(
                page.locator("//div[@class='buttons']//a").locator("nth=-1").textContent());

    }

}

