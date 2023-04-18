package org.mln;

import com.microsoft.playwright.*;
import org.junit.Test;

public class ContextConcept {

    @Test
    public void sameContext() {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();
        Page page1 = browserContext.newPage();
        page1.navigate("https://bookcart.azurewebsites.net/login");
        page1.locator("//input[@id='mat-input-7' or @data-placeholder='Username']").fill(
                "lm");
        page1.locator("//input[@id='mat-input-8' or @data-placeholder='Password']").fill("testPassword@123");
        page1.locator("(//span[text()='Login'])[2]").click();
        String userName = page1.locator("//mat-icon[.='account_circle']//parent::span").textContent().split(" ")[1];
        System.out.println(userName);


        Page page2 = browserContext.newPage();
        userName = page2.locator("//mat-icon[.='account_circle']//parent::span").textContent().split(" ")[1];
        System.out.println(userName);
    }
}
