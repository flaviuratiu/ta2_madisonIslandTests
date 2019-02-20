package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest {

    @Test
    public void searchByOneKeywordTest() {
        System.setProperty("webdriver.chrome.driver",
                AppConfig.getChromeDriverPath());
        WebDriver driver = new ChromeDriver();
        driver.get(AppConfig.getSiteUrl());
        System.out.println("Opened homepage");

        String keyword = "vase";
        driver.findElement(By.className("input-text")).
                sendKeys(keyword + Keys.ENTER);
        System.out.println("Pressed enter in search field.");

        List<WebElement> productNameContainers =
                driver.findElements(By.cssSelector(".product-name > a"));

        for (WebElement container : productNameContainers) {
            String productName = container.getText();

            System.out.println(productName);
            assertThat("Some of the product names do not contain the searched keyword",
                   productName, containsString(keyword.toUpperCase()));
        }
    }
}
