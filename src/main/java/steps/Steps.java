package steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.PageMarketNoutbook;
import pages.PageYandexMarket;
import pages.YandexMainPage;
import utils.Screensooter;

public class Steps {

    @Step("Переход на страницу Яндекс сервиса: {textTitle}")
    public static void goToMarket(YandexMainPage yandexMainPage, String textTitle, WebDriver driver) {
        Assertions.assertTrue(yandexMainPage.openYandexMarket(textTitle),
                "Страница " +textTitle + " не найдена");
        Screensooter.getScreen(driver);
    }

    @Step("Выбор категории продукта: {textProduct}")
    public static void ChooseMarketProduct(PageYandexMarket pageYandexMarket, String textProduct, WebDriver driver) {
        Assertions.assertTrue(pageYandexMarket.getMarketProductList(textProduct),
                "Раздел " + textProduct + " не найден");
        Screensooter.getScreen(driver);
    }

    @Step("Выбор подкатегории {textSubProduct}")
    public  static void goToMarketNoutBook(PageYandexMarket pageYandexMarket, String textSubProduct, WebDriver driver) {
        Assertions.assertTrue(pageYandexMarket.getComputerProductList(textSubProduct),
                "Раздела " + textSubProduct + " не найдено");
        Screensooter.getScreen(driver);
    }

    @Step("Установка центы от {priceFrom} до {priceTo}")
    public static void inputPriceProduct(PageMarketNoutbook pageMarketNoutbook, String priceFrom, String priceTo, WebDriver driver) {
        Assertions.assertEquals("10000", pageMarketNoutbook.castPriceFrom(priceFrom), "Заявленная цена " + priceFrom + "отличается");
        Assertions.assertEquals("90000", pageMarketNoutbook.castPriceTo(priceTo), "Заявленная цена " + priceTo + "отличается");
        Screensooter.getScreen(driver);
    }

    @Step("Выбор производителя {manufacture}")
    public static void inputCheckoboxManufacturer(PageMarketNoutbook pageMarketNoutbook, String manufacture, WebDriver driver) {
        Assertions.assertTrue(pageMarketNoutbook.clickCheckbox(manufacture), "Производитель " + manufacture + " не найден");
        Screensooter.getScreen(driver);
    }

    @Step("Проверка. что на странице отображается 12 элементов")
    public static void check12Item(PageMarketNoutbook pageMarketNoutbook) {
        pageMarketNoutbook.clickDropdown();
        Assertions.assertEquals(12, pageMarketNoutbook.searchRezultNoutBookSize(), "На странице не 12 товаров");
    }

    @Step("Сравнение продукта {productA} с продуктом {productB}")
    public  static void compareProductWithNewProduct(PageMarketNoutbook pageMarketNoutbook, String productA, String productB) {
        Assertions.assertEquals(pageMarketNoutbook.firstElementPage(), pageMarketNoutbook.firstElementNewPage(),
                "Товар не соответствует заявленному");
    }
}
