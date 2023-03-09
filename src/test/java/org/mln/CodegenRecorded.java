package org.mln;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
public class CodegenRecorded {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://letcode.in/");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Work-Space")).click();
            page.locator("div").filter(new Locator.FilterOptions().setHasText("Interact with different types of input fields")).first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Edit")).click();
            page.locator("#join").click();
            page.locator("#join").fill("I am good a boy");
        }
    }

}
