package org.mln;

import com.microsoft.playwright.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;

public class HTTPAuthentication {
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
    public void testHTTPAuthetentication() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        page = context.newPage();
        page.navigate(("https://the-internet.herokuapp.com/basic_auth"));
        System.out.println(page.locator("//h3[.='Basic Auth']/following-sibling::p").textContent());
    }
}
