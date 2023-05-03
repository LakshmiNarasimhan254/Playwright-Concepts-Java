package org.mln;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class FrameInteractions {

    @Test
    public void frameInteraction() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/frame");
        Frame frame = page.frame("firstFr");
        frame.getByPlaceholder("Enter name").type("Lakshmi");
        frame.getByPlaceholder("Enter email").type("Mohan");
        Assert.assertTrue(frame.locator("p:has-text('You have entered')").textContent().equalsIgnoreCase("You have entered Lakshmi Mohan"));
    }
    @Test
    public void frameInnerFrame() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/frame");
        Frame innerFrame = page.frameByUrl(Pattern.compile("innerFrame"));
        innerFrame.getByPlaceholder("Enter email").type("Mohan");

    }

    @Test
    public void childFrames(){
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/frame");
        Frame frame = page.frame("firstFr");
        List<Frame> childFrameList =frame.childFrames();
        childFrameList.forEach((f)->{
            System.out.println(f.url());

        });

    }
    @Test
    public void frameLocator(){
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/frame");
        FrameLocator innerFrame = page.frameLocator("//div/*[@name='firstFr']");
        innerFrame.getByPlaceholder("Enter email").fill("My Name");
    }

    @Test
    public void exerciseFrames(){
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://the-internet.herokuapp.com/iframe");
        page.frameLocator("//iframe[@title='Rich Text Area']").locator("//body[@id='tinymce']").type("Lakshmi");
        page.frameLocator("//iframe[@title='Rich Text Area']").locator("//body[@id='tinymce']").press("Control+a");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Bold")).click();
       Assert.assertTrue( page.locator("//div[@data-index='1']").textContent().equalsIgnoreCase("Strong"));
    }

    }


