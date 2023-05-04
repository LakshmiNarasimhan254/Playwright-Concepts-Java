package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

public class ScreenShot {

    @Test
    public void PageScreenShot(){
      Page page= Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
      page.navigate("https://www.usinflationcalculator.com/");
      //visible screen
      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("ScreenShotSample.png")));

      //full page
      page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("fullpageShotSample.png")));
    }

    @Test
    public void LocatorScreenShot(){
        Page page= Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
        page.navigate("https://www.usinflationcalculator.com/");

        Locator locator=   page.frameLocator("#tdi_58 iframe >> nth=0").locator("#XLEW_1_4_12");
        locator.click();
        locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("LocatorSample.png")));

        page.screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("InitialLocatorSample.png"))
                        .setFullPage(true)
                        .setCaret(ScreenshotCaret.INITIAL)
                        .setMask(Arrays.asList(locator))
                        .setOmitBackground(true)
                );

    }
    }

