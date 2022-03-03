package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utils.WebDriverInitialize;


public class BaseTest {
    protected WebDriver chromeDriver;

    @BeforeEach
    public  void before () {
        WebDriverInitialize.initChrome();
        chromeDriver = WebDriverInitialize.getCurrentDriver();
    }

    @AfterEach
    public void testGoogle () {
        WebDriverInitialize.killCurrentDriver();
    }
}
