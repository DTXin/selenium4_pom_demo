package org.example;

import org.example.Base.Driver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.example.Common.PageManager.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BingTest {
    protected static final Logger logger = LogManager.getLogger();

    @BeforeClass
    public static void initBrowser() {
        Driver.startBrowser();
    }

    @AfterClass
    public static void cleanup() {
        Driver.stopBrowser();
    }

    @Test
    public void searchTextInBing_WithoutSeleniumPageFactory() {
        getBingMainPage().navigateX("http://bing.com");
        getBingMainPage().search("Automate The Planet");
        getBingMainPage().resultsCount(",000 Results");
    }
}
