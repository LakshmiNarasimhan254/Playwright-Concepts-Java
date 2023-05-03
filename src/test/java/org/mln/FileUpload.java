package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUpload {
    @Test
    public void singleFileUpload() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/file");
        page.getByText(" Choose a file… ").setInputFiles(Paths.get("sample.xlsx"));
        page.close();

    }
    @Test
    public void multipleFileUpload() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();

        page.navigate("https://www.sendgb.com/");
        page.locator("//span[text()='OK']").click();




        page.locator("[name='qqfile']").setInputFiles(new Path[]{
                Paths.get("sample.xlsx"),
                Paths.get("filedown.xlsx")
        });

        FileChooser fileChooser= page.waitForFileChooser(()->{
            page.locator("id=files").click();
        });
        System.out.println(fileChooser.isMultiple());
        page.close();

    }
    @Test
    public void fileUploadMethod2() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();

        page.navigate("https://www.sendgb.com/");
        page.locator("//span[text()='OK']").click();
        FileChooser fileChooser= page.waitForFileChooser(()->{
            page.locator("id=files").click();
        });
        System.out.println(fileChooser.isMultiple());

        //Multiple file
        fileChooser.setFiles(new Path[]{
                Paths.get("sample.xlsx"),
                Paths.get("filedown.xlsx")
        });

        //Single File
        fileChooser.setFiles(
                Paths.get("sample.xlsx")
        );

        page.close();
    }
}
