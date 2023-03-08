package org.mln;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LaunchBrowser {

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
    public void launchChromium() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/"));
        String title = page.title();
        String url = page.url();
        System.out.println("Page Title  " + title);
        System.out.println("Page url  " + url);

    }
    @Test
    public void launchFirefox() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/"));
        String title = page.title();
        System.out.println("Page Title  " + title);

    }
    @Test
    public void launchWebkit() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/"));
        String title = page.title();
        System.out.println("Page Title  " + title);


    }
    @Test
    public void launchChromeLocal() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome"));
        page = browser.newPage();
        page.navigate(("https://letcode.in/"));
        String title = page.title();
        System.out.println("Page Title  " + title);

    }
    @Test
    public void launchEdgeLocal() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(false)
                        .setChannel("msedge"));
        page = browser.newPage();
        page.navigate(("https://letcode.in/"));
        String title = page.title();
        System.out.println("Page Title  " + title);

    }


}
