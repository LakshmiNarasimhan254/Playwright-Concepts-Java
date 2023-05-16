package org.mln;

import com.microsoft.playwright.*;
import org.junit.Test;

import java.nio.file.Paths;

public class skipAuthentication {

    @Test
    public void getCookie(){
        BrowserContext context = Playwright.create().chromium().launch( new BrowserType.LaunchOptions().setHeadless(false)).newContext();
        Page page  = context.newPage();
        page.navigate("https://bookcart.azurewebsites.net/");
        page.locator("//span[text()='Login']").click();
        page.locator("//input[@formcontrolname='username']").type("lakme88");
        page.locator("//input[@formcontrolname='password']").type(("Check@123"));
        page.locator ("//span[text()='Login']").last().click();
        String userName = page.locator("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]").textContent();
        System.out.println(userName.split(" ")[1]);

        //generate cookies
      context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));

    }
    @Test
    public void UseCookie(){
        BrowserContext context = Playwright.create().chromium().launch( new BrowserType.LaunchOptions().setHeadless(false)).newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json"))
        );
        Page page  = context.newPage();
        page.navigate("https://bookcart.azurewebsites.net/");
//        page.locator("//span[text()='Login']").click();
//        page.locator("//input[@formcontrolname='username']").type("lakme88");
//        page.locator("//input[@formcontrolname='password']").type(("Check@123"));
//        page.locator ("//span[text()='Login']").last().click();
        String userName = page.locator("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]").textContent();
        System.out.println(userName.split(" ")[1]);



    }
}
