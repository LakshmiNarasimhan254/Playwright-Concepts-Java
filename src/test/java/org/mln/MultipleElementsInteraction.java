package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.Test;

public class MultipleElementsInteraction {

    @Test
    public void getAllLinks(){
       Page page= Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
       page.navigate("https://letcode.in/elements");
       page.locator("[name='username']").type("lakshminarasimhan254");
       page.locator("button:has-text('Search')").click();
       Locator gitHubLinks = page.locator("//ol/li/a");
       gitHubLinks.last().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

       int linkCount = gitHubLinks.count();
       System.out.println("The Total number of Links is " +linkCount);
        for (int iCount= 0;iCount <linkCount; iCount++) {
            System.out.println(gitHubLinks.nth(iCount).textContent());

        }
    }
}
