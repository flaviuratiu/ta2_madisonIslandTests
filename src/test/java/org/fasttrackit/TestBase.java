package org.fasttrackit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBase {

    protected WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.initDriver("firefox");
        driver.get(AppConfig.getSiteUrl());
        System.out.println("Opened homepage");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
