package org.example.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
    protected static final Logger logger = LogManager.getLogger();

    private static WebDriver browser;
    private static WebDriverWait browserWait;

    private enum BrowserType {
        FIREFOX,
        CHROMIUM,
        EDGE
    }

    public static WebDriver getBrowser() {
        if (browser == null) {
            throw new NullPointerException(
                    "The WebDriver browser instance was not initialized. You should first call the start method.");
        }
        return browser;
    }

    public static void setBrowser(WebDriver browser) {
        Driver.browser = browser;
    }

    public static WebDriverWait getBrowserWait() {
        if (browser == null) {
            throw new NullPointerException(
                    "The WebDriverWait browser instance was not initialized. You should first call the start method.");
        }
        return browserWait;
    }

    public static void setBrowserWait(WebDriverWait browserWait) {
        Driver.browserWait = browserWait;
    }

    // Add arguments to Chrome
    private static ChromeOptions setChromiumOption() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--suppress-message-center-popups");
        return options;
    }

    // Add arguments to FireFox
    private static FirefoxOptions setFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

    // Add arguments to Edge
    private static EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        return options;
    }

    // startBrowser function with 2 parameter
    public static void startBrowser(BrowserType browserType, int defaultTimeout) {
        switch (browserType) {
            case FIREFOX:
                logger.info("========= Logger: Init firefox browser =========");
                setBrowser(new FirefoxDriver(setFirefoxOptions()));
                break;

            case CHROMIUM:
                logger.info("========= Logger: Init chromium browser =========");
                setBrowser(new ChromeDriver(setChromiumOption()));
                break;

            case EDGE:
                logger.info("========= Logger: Init edge browser =========");
                setBrowser(new EdgeDriver(setEdgeOptions()));
                break;

            default:
                break;
        }
        setBrowserWait(new WebDriverWait(getBrowser(), Duration.ofSeconds(defaultTimeout)));
    }

    // startBrowser function with 1 parameter and default of timeout = 30 seconds
    public static void startBrowser(BrowserType browserType) {
        startBrowser(browserType, 30);
    }

    // startBrowser function with 0 parameter. Set chromium browser is default
    public static void startBrowser() {
        startBrowser(BrowserType.CHROMIUM);
    }

    public static void stopBrowser() {
        logger.info("========= Logger: Stop and Close browser =========");
        getBrowser().quit();
        setBrowser(null);
        setBrowserWait(null);
    }
}
