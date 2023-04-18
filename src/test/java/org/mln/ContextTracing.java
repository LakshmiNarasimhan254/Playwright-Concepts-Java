package org.mln;

import com.microsoft.playwright.*;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ContextTracing {

    @Test
    public void learnContextTracing() {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        Browser browser = browserType.launch(
                new BrowserType
                        .LaunchOptions()
                        .setHeadless(false));
        BrowserContext browserContext = browser.newContext();
        browserContext.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true));


        Page page = browserContext.newPage();
        //input
        page.navigate(("https://letcode.in/edit"));
        page.locator("//input[@id='fullName']").type("Lakshmi Mohan");
        page.locator("//input[@id='join']").type("Boy");
        page.press("//input[@id='join']", "End");
        page.type("//input[@id='join']", "Boy");
        page.fill("//input[@id='join']", "Boy");

        //Login
        page.navigate("https://bookcart.azurewebsites.net/login");
        page.locator("//input[@id='mat-input-7' or @data-placeholder='Username']").fill(
                "lm");
        page.locator("//input[@id='mat-input-8' or @data-placeholder='Password']").fill("testPassword@123");
        page.locator("(//span[text()='Login'])[2]").click();
        String userName = page.locator("//mat-icon[.='account_circle']//parent::span").textContent().split(" ")[1];
        System.out.println(userName);

        browserContext.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("trace.zip")) );

        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }
}
