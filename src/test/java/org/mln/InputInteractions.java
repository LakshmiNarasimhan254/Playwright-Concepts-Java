package org.mln;

import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputInteractions {
    Playwright playwright;
    Browser browser;
    Page page;

    @Before
    public void initialSetup() {
        playwright = Playwright.create();

    }

    @After
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    //erase the text already present and enters the text
    public void fillText(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        page.locator("//input[@id='fullName']").type("Lakshmi Mohan");
    }

    @Test
    //Appends the text before the text that is already present
    public void typeText_Beginning(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        page.locator("//input[@id='join']").type("Boy");

    }

    @Test
    //Appends the text after the text that is already present
    public void typeText_End(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        Locator locator= page.locator("//input[@id='join']");
        locator.press("End");
        locator.type("Boy");
        locator.press("Tab");

    }

    @Test
    //another way to access the elements
    public void elementLocatorWays(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        page.type("//input[@id='fullName']","Lakshmi Mohan");

        page.press("//input[@id='join']","End");
        page.type("//input[@id='join']","Boy");
        page.fill("//input[@id='join']","Boy");

    }

    @Test
    public void getAttributes(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        String attribute = page.locator("#getMe").getAttribute("value");
        System.out.println("The text value in the attribute is "+ attribute);

    }

    @Test
    public void clearText(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/edit"));
        page.locator("#clearMe").clear();
    }



}
