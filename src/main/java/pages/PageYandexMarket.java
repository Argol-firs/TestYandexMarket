package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GoActions;
import utils.Waiting;
import utils.WebDriverInitialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class for working with the YandexMarket page
 */
public class PageYandexMarket {

    /**
     * Field for storing arrays of data
     */
    private WebDriver chromeDriver;

    private String catalogButton = "//button[@id='catalogPopupButton' and @type='button' and @aria-label='Каталог']";
    private String selectorProduct = "//div//li[@data-zone-name='category-link']";
    private String selectorProductURL = ".//a[@href]";
    private String  selectorProductNamePage = ".//span";

    private String selectorComputerProduct ="//div[contains(@data-apiary-widget-name, 'NavigationTree')]//div[@data-zone-name]";
    private String selectorComputerURL = ".//a[@href]";

    private List<WebElement> searchProduct = new ArrayList<>();
    private List<Map<String, Object>> productList = new ArrayList<>();

    private List<WebElement> computerProduct = new ArrayList<>();
    private List<Map<String, Object>> computerList = new ArrayList<>();

    public PageYandexMarket() {
        this.chromeDriver = WebDriverInitialize.getCurrentDriver();
    }

    /**
     * Catalog button click method
     */
    public void clickCatalogButton() {
        chromeDriver.findElement(By.xpath(catalogButton)).click();
    }

    /**
     * Method for obtaining a list of YandexMarket products
     * @see YandexMainPage#getYandexServices()
     */
    public List<Map<String, Object>> getMarketServices() {
        Waiting.waitUntilElementBeVisible(chromeDriver.findElement(By.xpath("//div[@aria-labelledby='catalogPopupButton']")));
        searchProduct = chromeDriver.findElements(By.xpath(selectorProduct));
        for (WebElement result : searchProduct) {
            productList.add(Map.of(
                            "WEB_ELEMENT", result,
                            "URL", result.findElement(By.xpath(selectorProductURL)),
                            "NAME_PAGE", result.findElement(By.xpath(selectorProductNamePage)).getText()
                    )
            );
        }
        return productList;
    }


    /**
     * The method finds the requested element in the collection and focuses the mouse position on it
     * @see YandexMainPage#openYandexMarket(String)
     */
    public boolean getMarketProductList(String namePage) {
        WebElement linkProduct = (WebElement) getMarketServices().stream()
                .filter(x -> x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");
        GoActions.action.moveToElement(linkProduct.findElement(By.xpath(selectorProductURL))).build().perform();
        return true;
    }

    /**
     * Method for obtaining a list of items on the Computers tab
     * @see YandexMainPage#getYandexServices()
     */
    public List<Map<String, Object>> getComputerServices() {
        computerProduct= chromeDriver.findElements(By.xpath(selectorComputerProduct));
        for (WebElement result : computerProduct) {
            computerList.add(Map.of(
                            "WEB_ELEMENT", result,
                            "URL", result.findElement(By.xpath(selectorComputerURL)),
                            "NAME", result.getAttribute("textContent")
                    )
            );
        }
        return computerList;
    }

    /**
     * The method finds the requested element in the collection and follows its link
     * @see YandexMainPage#openYandexMarket(String)
     */
    public boolean getComputerProductList(String namePage) {
        WebElement linkNoteBook = (WebElement) getComputerServices().stream()
                .filter(x -> x.get("NAME").toString().equals(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");
        linkNoteBook.findElement(By.xpath(selectorComputerURL)).click();
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        for (String tab : tabs) {
            chromeDriver.switchTo().window(tab);
        if (chromeDriver.getTitle().contains(namePage))
            return true;
        }
        Assertions.fail("Не найден раздел " + namePage);
        return false;
    }
}
