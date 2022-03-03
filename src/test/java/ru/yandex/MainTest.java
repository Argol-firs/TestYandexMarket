package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.PageMarketNoutbook;
import pages.YandexMainPage;
import pages.PageYandexMarket;
import steps.Steps;

public class MainTest extends BaseTest {

    @Feature("Проверка ЯндексМаркета")
    @DisplayName("Проверка результата поиска товара на ЯндексМаркете")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource("https://yandex.ru/, Маркет")
    public void openYandex(String search, String services) {
        chromeDriver.get(search);
        YandexMainPage yandexMainPage = new YandexMainPage();
        Steps.goToMarket(yandexMainPage, services, chromeDriver);
        PageYandexMarket pageYandexMarket = new PageYandexMarket();
        pageYandexMarket.clickCatalogButton();
        Steps.ChooseMarketProduct(pageYandexMarket, "Компьютеры", chromeDriver);
        Steps.goToMarketNoutBook(pageYandexMarket, "Ноутбуки", chromeDriver);
        PageMarketNoutbook pageMarketNoutbook = new PageMarketNoutbook();
        Steps.inputPriceProduct(pageMarketNoutbook, "10000", "90000", chromeDriver);
        Steps.inputCheckoboxManufacturer(pageMarketNoutbook, "HP", chromeDriver);
        Steps.inputCheckoboxManufacturer(pageMarketNoutbook, "Lenovo", chromeDriver);
        Steps.check12Item(pageMarketNoutbook);
        pageMarketNoutbook.firstElementPage();
        pageMarketNoutbook.clickSearchElementCollection();
        pageMarketNoutbook.firstElementNewPage();
        Steps.compareProductWithNewProduct(pageMarketNoutbook, pageMarketNoutbook.firstElementPage(), pageMarketNoutbook.firstElementNewPage());
    }
}
