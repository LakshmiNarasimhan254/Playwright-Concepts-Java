package org.mln;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

public class AlertInteractions {

    Playwright playwright;
    Browser browser;
    Page page;

    @Before
    public void initialSetup() {
        playwright = Playwright.create();

    }

    @After
    public void tearDown() {
        //page.close();
        // browser.close();
        // playwright.close();
    }

    @Test

    public void testSimpleAlert() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/alert"));

        page.onDialog(dialog->{
            System.out.println(dialog.message());
            dialog.dismiss();

        });
        page.locator("id=accept").click();

    }
    @Test
    public void testConfirmAlert() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/alert"));

        page.onDialog(dialog->{
            System.out.println(dialog.message());
            dialog.accept();

        });
        page.locator("id=confirm").click();

    }
    @Test
    public void testPromptAlert() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/alert"));

        page.onDialog(dialog->{
            System.out.println(dialog.message());
            dialog.accept("My is Lakshmi Mohan");
        });
        page.locator("id=prompt").click();
        System.out.println(page.locator("id=myName").textContent());
    }

    @Test
    public  void testModernAlert(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/alert"));

        page.locator("id=modern").click();
        System.out.println(page.locator("//p[text()='Modern Alert - Some people address me as sweet alert as well ']").textContent());
        page.locator("//button[@class='modal-close is-large']").click();

    }

    @Test
    public void testMultipleAlert(){
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/alert"));
        Consumer<Dialog> alert1 =new Consumer<Dialog>(){
            @Override
            public void accept(Dialog dialog) {
                System.out.println(dialog.message());
                dialog.accept("My First Name is Lakshmi");
               page.offDialog(this);
            }
        };
        page.onDialog(alert1);
        page.locator("id=prompt").click();
        String firstName = page.locator("id=myName").textContent();
        Consumer<Dialog> alert2 =new Consumer<Dialog>(){
            @Override
            public void accept(Dialog dialog) {
                System.out.println(dialog.message());
                dialog.accept("My Last Name is Mohan");
                page.offDialog(this);
            }
        };
        page.onDialog(alert2);
        page.locator("id=prompt").click();
        String lastName = page.locator("id=myName").textContent();
        System.out.println(firstName + " " +lastName);
    }
}

