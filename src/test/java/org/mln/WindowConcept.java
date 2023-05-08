package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

import java.util.List;

public class WindowConcept {

    @Test
    public void singleWindow() {
        Page page = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
        page.navigate("https://letcode.in/windows");
        Page popup = page.waitForPopup(() -> {
            page.locator("id=home").click();
        });
        System.out.println(popup.title());
        page.close();
        popup.close();
    }

    @Test
    //Refer video -https://youtu.be/DQiYwZU84WQ for understanding wait for popup
    public void multipleWindow() {
        Page page = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
        page.navigate("https://letcode.in/windows");

        page.waitForPopup(new Page
                        .WaitForPopupOptions()
                        .setPredicate((p) ->
                                p.context().pages().size() == 3)
                , () -> {
                    page.locator("id=multi").click();
                });


        List<Page> pages = page.context().pages();
        pages.forEach((tabs) -> {
                    System.out.println(tabs.url());
                    tabs.close();
                }

        );



    }
}
