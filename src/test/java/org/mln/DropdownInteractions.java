package org.mln;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DropdownInteractions {

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
    //erase the text already present and enters the text
    public void testSelectSingleOption() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/dropdowns"));

        //selection using page
        page.selectOption("//select[@id='fruits']", "0");

        //selection using element handle with values
        page.locator("//select[@id='fruits']").selectOption("1");
        System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());

        page.locator("//select[@id='fruits']").selectOption(new SelectOption().setValue("0"));
        System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());

        //selection using element handle with text/label
        page.locator("//select[@id='fruits']").selectOption(new SelectOption().setLabel("Orange"));
        System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());

        //selection using element handle with index
        page.locator("//select[@id='fruits']").selectOption(new SelectOption().setIndex(4));
        System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());
    }


    @Test
    public void testSelectMultipleOption() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/dropdowns"));
        Locator multiSelectDD =  page.locator("#superheros");
        multiSelectDD.selectOption(new String[]{"Ant-Man", "Batman","Aquaman"});
            System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());
    }

    @Test
    public void testDropdownSize() {
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(("https://letcode.in/dropdowns"));
        Locator options =  page.locator("//select[@id='lang']");
        Integer optionsCount = options.locator("//option").count();
        options.selectOption(new SelectOption().setIndex(optionsCount-1));
        System.out.println("The Text is " + page.locator("//p[@class=\"subtitle\"]").textContent());

        List<String> optionList = options.allInnerTexts();
        optionList.forEach(i-> System.out.println(i));

    }


}
