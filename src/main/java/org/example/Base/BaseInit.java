package org.example.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.Common.ICommonPage;
import org.openqa.selenium.WebDriver;

public abstract class BaseInit implements ICommonPage {
    protected static final Logger logger = LogManager.getLogger();

    protected WebDriver browser;
    private String baseUrl = null;

    public BaseInit() {
        browser = Driver.getBrowser();
    }

    public void setBaseUrl(String url) {
        if (url == null) {
            logger.info("========= Logger: Set url: " + baseUrl + " =========");
            baseUrl = url;
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
