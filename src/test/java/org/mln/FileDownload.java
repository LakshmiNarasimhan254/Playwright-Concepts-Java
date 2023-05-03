package org.mln;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class FileDownload {

    @Test
    public void fileDownload() {
        Page page = Playwright
                .create()
                .chromium()
                .launch(new BrowserType
                        .LaunchOptions()
                        .setHeadless(false))
                .newContext()
                .newPage();
        page.navigate("https://letcode.in/file");
        Download download = page.waitForDownload(() ->
        {
            page.getByText("Download Excel").click();
        });
        System.out.println(download.url());
        System.out.println(download.path());
        System.out.println(download.failure());
        System.out.println(download.page());
        System.out.println(download.suggestedFilename());
        download.saveAs(Paths.get(download.suggestedFilename()));
        download.saveAs(Paths.get("filedown.xlsx"));
        page.close();

    }
}
