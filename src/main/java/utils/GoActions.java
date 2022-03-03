package utils;


import org.openqa.selenium.By;

import java.util.function.Consumer;


/**
 * API for emulating custom gestures
 */
public class GoActions {
    public static org.openqa.selenium.interactions.Actions action = new org.openqa.selenium.interactions.Actions(WebDriverInitialize.getCurrentDriver());

    public static Consumer<By> hover = (By by) -> {
        action.moveToElement(WebDriverInitialize.getCurrentDriver().findElement(by))
                .perform();
    };
}
