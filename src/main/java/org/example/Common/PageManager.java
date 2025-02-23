package org.example.Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.Base.Driver;
import org.example.PageObject.BingMainPage;
import org.openqa.selenium.support.PageFactory;

public class PageManager {
    protected static final Logger logger = LogManager.getLogger();
    private static BingMainPage bingMainPage;

    public static ICommonPage getInstance(ICommonPage iPage, String className) {
        try {
            if (iPage == null) {
                logger.info("========= Logger: New instant of object >>> `{}` =========", className);
                iPage = (ICommonPage) Class.forName(className).getDeclaredConstructor().newInstance();
            }
            PageFactory.initElements(Driver.getBrowser(), iPage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return iPage;
    }

    public static BingMainPage getBingMainPage() {
        bingMainPage = (BingMainPage) getInstance(bingMainPage, BingMainPage.class.getName());
        return bingMainPage;
    }
}
