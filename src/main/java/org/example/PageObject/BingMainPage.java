package org.example.PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.example.Base.BaseInit;

public class BingMainPage extends BaseInit {
    protected static final Logger logger = LogManager.getLogger();

    @FindBy(id = "sb_form_q")
    WebElement searchBox;

    @FindBy(xpath = "//label[@for='sb_form_go']")
    WebElement goButton;

    @FindBy(id = "b_tween")
    WebElement resultsCountDiv;

    public void navigateX(String url) {
        logger.info("========= Logger: Navigate to url >>> `{}` =========", url);
        browser.navigate().to(url);
    }

    public void search(String textToType) {
        logger.info("========= Logger: Search with texttext >>> `{}` =========", textToType);
        searchBox.clear();
        searchBox.sendKeys(textToType);
        goButton.click();
    }

    public void resultsCount(String expectedCount) {
        Assert.assertTrue(resultsCountDiv.getText().contains(expectedCount),
                "The results DIV doesn't contain the specified text.");
    }
}
