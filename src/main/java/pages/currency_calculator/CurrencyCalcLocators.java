package pages.currency_calculator;

import org.openqa.selenium.By;

public interface CurrencyCalcLocators {
    By rateTableLocator = By.xpath("//div[contains(@class, 'rate-table ')]");
    By seeRatesButtonLocator = By.xpath("//a[contains(@class, 'btn btn-success')]");
    By currencyBlockLocator = By.xpath("//section[@class= 'text-section  ']");
    By sellInputLocator = By.xpath("//label[text()= 'Sell']/following-sibling::input[contains(@class, 'form-control')]");
    By buyInputLocator = By.xpath("//label[text()= 'Buy']/following-sibling::input[contains(@class, 'form-control')]");
    By lazyLoaderImageLocator = By.xpath("//div[contains(@class, 'text-center ng-hide')]");
    By langFooterButtonLocator = By.cssSelector("span.js-localization-popover");
    By langChangeButtonLocator = By.cssSelector("#countries-dropdown");
    By langListLocator = By.cssSelector("ul.dropdown-menu[aria-labelledby=countries-dropdown]>li");
    By currencyNameLocator = By.xpath("//span[contains(@class, 'ui-select-match-text pull-left')]/span");
    By tableRatesListLocator = By.xpath("//tr[@class='ng-scope']");
}
