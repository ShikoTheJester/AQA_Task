package site.currency_calculator;

import org.openqa.selenium.By;

public interface CurrencyCalcLocators {
    By RATE_TABLE = By.xpath("//div[contains(@class, 'rate-table ')]");
    By SEE_RATES_BUTTON = By.xpath("//a[contains(@class, 'btn btn-success')]");
    By CURRENCY_BLOCK = By.xpath("//section[@class= 'text-section  ']");
    By SELL_INPUT = By.xpath("//label[text()= 'Sell']/following-sibling::input[contains(@class, 'form-control')]");
    By BUY_INPUT = By.xpath("//label[text()= 'Buy']/following-sibling::input[contains(@class, 'form-control')]");
    By LAZY_LOADER_IMAGE = By.xpath("//div[contains(@class, 'text-center ng-hide')]");
    By LANG_FOOTER_BUTTON = By.cssSelector("span.js-localization-popover");
    By LANG_CHANGE_BUTTON = By.cssSelector("#countries-dropdown");
    By LANG_LIST = By.cssSelector("ul.dropdown-menu[aria-labelledby=countries-dropdown]>li");
    By CURRENCY_NAME = By.xpath("//span[contains(@class, 'ui-select-match-text pull-left')]/span");
    By TABLE_RATES_LIST = By.xpath("//tr[@class='ng-scope']");
}
