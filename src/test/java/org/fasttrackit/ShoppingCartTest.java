package org.fasttrackit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTest {

    @Test
    public void addToCartFromSearchResultsTest() {
        System.setProperty("webdriver.chrome.driver",
                "src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fasttrackit.org/selenium-test");

        String keyword = "vase";
        driver.findElement(By.className("input-text")).
                sendKeys(keyword + Keys.ENTER);

        // extract product name from XPath to a variable for further use
        // make sure the XPath would still contain the product name
        String productName = "Herald Glass Vase";
        driver.findElement(By.xpath(
                "//div[@class='product-info' and .//a[text()='"
                        + productName + "']]//button[@title='Add to Cart']"))
                .click();

        String successMessage = driver.findElement(By.className("success-msg"))
                .getText();

        assertThat("Unexpected success message", successMessage,
                is(productName + " was added to your shopping cart."));

        WebElement productNameInCart = driver.findElement(By.xpath(
                "//table[@id='shopping-cart-table']//a[text()='" +
                        productName + "']"));

        assertThat("Product not displayed in cart.",
                productNameInCart.isDisplayed());
    }
}
