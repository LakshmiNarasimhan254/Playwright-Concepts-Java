package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

public class InbuiltAssertion {

    @Test
    public void inBuiltPageAssersionTest(){
       Page page=  Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
       page.navigate("https://letcode.in/edit");
       assertThat(page).hasTitle("Interact with Input fields");
       assertThat(page).hasURL(Pattern.compile("^https://[\\w-]+.in\\/edit$"));


    }

    @Test
    public void inBuiltLocatorAssersionTest(){
        Page page=  Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
        page.navigate("https://letcode.in/edit");
        Locator header = page.locator("h1");
        assertThat(header).containsText("put");
    }

    @Test
    public void inBuiltLocatorNegativeAssersionTest(){
        Page page=  Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
        page.navigate("https://letcode.in/radio");
        //To override the assertion time out of 5 secs to any values for ex 10 seconds
         setDefaultAssertionTimeout(10000);
         assertThat(page.locator("[id='no']")).not().isChecked();

    }



}
