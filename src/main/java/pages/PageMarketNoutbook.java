package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GoActions;
import utils.Waiting;
import utils.WebDriverInitialize;

import java.util.ArrayList;
import java.util.List;

public class PageMarketNoutbook {

    /**
     * Field for storing arrays of data
     */
    private WebDriver chromeDriver;

    private String castFrom = "//legend[contains(text(), 'Цена')]/..//p[@data-range-input-type='from']//input[@name='Цена от']";
    private String castTo = "//legend[contains(text(), 'Цена')]/..//p[@data-range-input-type='to']//input[@name='Цена до']";
    private String chooseManufacturer = "//fieldset[@data-autotest-id='7893318']//li//label";
    private String dropDownButton = "//button[contains(@id, 'dropdown-control')]";
    private String dropDownButton12Items = "//button[contains(text(), 'Показывать по 12')]";
    private String loaderList = "//div[@class='_12KyU _38i7T' and @data-tid='8bc8e36b']";
    private String rezultSearch = "//div[@data-zone-name='SearchResults']//h3//a";
    private String rezultSearchNew = "//article//h3//a[@title]";
    private String headerSearch = "//input[@type='text' and @id='header-search']";
    private String searchButton = "//button[@data-r='search-button']";
    String productMain;
    String productMainNew;

    private List<WebElement> Manufactured = new ArrayList<>();
    List<WebElement> rezult = new ArrayList<>();
    List<WebElement> rezultNew = new ArrayList<>();

    public PageMarketNoutbook() {
        this.chromeDriver = WebDriverInitialize.getCurrentDriver();
    }

    /**
     * Price from setting method
     * @param from string value of price from
     * @return set value
     */
    public String castPriceFrom(String from) {
        Waiting.waitUntilElementBeVisible(chromeDriver.findElement(By.xpath(castFrom)));
        chromeDriver.findElement(By.xpath(castFrom)).click();
        chromeDriver.findElement(By.xpath(castFrom)).sendKeys(from);
        Waiting.isElementNotExist(chromeDriver.findElement(By.xpath(loaderList)));
        return from;
    }

    /**
     * Price to setting method
     * @param to string value of price to
     * @return set value
     */
    public String castPriceTo(String to) {
        chromeDriver.findElement(By.xpath(castTo)).click();
        chromeDriver.findElement(By.xpath(castTo)).sendKeys(to);
        Waiting.isElementNotExist(chromeDriver.findElement(By.xpath(loaderList)));
        return to;
    }


    /**
     * Manufacturer selection and checkbox method
     * @param name vendor name value
     * @return check if the selected value matches the existing ones
     */
    public boolean clickCheckbox(String name) {
        Manufactured = chromeDriver.findElements(By.xpath(chooseManufacturer));
        for (WebElement check : Manufactured) {
            if (check.getText().equals(name)) {
                GoActions.action.moveToElement(check).build().perform();
                check.click();
                Waiting.isElementNotExist(chromeDriver.findElement(By.xpath(loaderList)));
                return true;
            }
        }
        Assertions.fail("Производитель " + name + " не найден");
        return false;
    }

    /**
     * The method finds the "show by" button and sets the value to "show by 12"
     */
    public void clickDropdown() {
        GoActions.action.moveToElement(chromeDriver.findElement(By.xpath(dropDownButton))).build().perform();
        chromeDriver.findElement(By.xpath(dropDownButton)).click();
        chromeDriver.findElement(By.xpath(dropDownButton12Items)).click();
        Waiting.isElementNotExist(chromeDriver.findElement(By.xpath(loaderList)));
    }

    /**
     * Method for counting the number of items shown on a page
     * @return webelement array size
     */
    public int searchRezultNoutBookSize(){
        rezult = chromeDriver.findElements(By.xpath(rezultSearch));
        return rezult.size();
    }

    /**
     * Storing the first element in an array of search results
     */
    public String firstElementPage(){
        rezultNew = chromeDriver.findElements(By.xpath(rezultSearchNew));
        productMain = rezultNew.get(0).getText();
        return productMain;
    }

    /**
     * The method enters the first saved element in the search bar and presses ENTER
     */
    public void clickSearchElementCollection() {
            chromeDriver.findElement(By.xpath(headerSearch)).click();
            chromeDriver.findElement(By.xpath(headerSearch)).sendKeys(firstElementNewPage());
            chromeDriver.findElement(By.xpath(headerSearch)).sendKeys(Keys.ENTER);
    }

    /**
     * The method finds the first element after the search and saves it to a variable
     */
    public String firstElementNewPage(){
        Waiting.waitUntilElementBeVisible(chromeDriver.findElement(By.xpath(rezultSearchNew)));
        rezultNew = chromeDriver.findElements(By.xpath(rezultSearchNew));
        productMainNew = rezultNew.get(0).getText();
        return productMainNew;
    }
}
