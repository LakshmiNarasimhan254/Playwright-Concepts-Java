package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Before;
import org.junit.Test;

public class LocatorStrategyGetBy {




    @Test
    public void getByTest(){
        Page page = Playwright.create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newContext()
                .newPage();

        page.navigate("https://bookcart.azurewebsites.net");
        page.getByText("Login").click();
        page.getByLabel("Username").fill("lm");
        page.getByLabel(("Password ")).fill(("testPassword@123"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"))
                //.first()
                //.nth(2)
                .last()
                .click();
        page.getByPlaceholder("Search books or authors").fill("Ha");
        page.getByRole(AriaRole.OPTION).nth(2).click();
        page.getByAltText("Book cover image").click();
        System.out.println(page.url());
    }
}
