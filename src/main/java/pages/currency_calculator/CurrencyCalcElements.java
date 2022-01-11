package pages.currency_calculator;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class CurrencyCalcElements implements CurrencyCalcLocators {

    private final String forDynamicLangElements = "//a[contains(., ";
    private final String someValue = "1340";
    private final String valueScript = "var data = arguments[0].value; return data;";
    private final String outerTextScript = "var data = arguments[0].outerText; return data;";

    private final SelenideElement rateTable = $(rateTableLocator);
    private final SelenideElement seeRatesButton = $(seeRatesButtonLocator);
    private final SelenideElement currencyBlock = $(currencyBlockLocator);
    private final SelenideElement sellInput = $(sellInputLocator);
    private final SelenideElement buyInput = $(buyInputLocator);
    private final SelenideElement lazyLoaderImage = $(lazyLoaderImageLocator);
    private final SelenideElement langFooterButton = $(langFooterButtonLocator);
    private final SelenideElement langChangeButton = $(langChangeButtonLocator);
    private final ElementsCollection langList = $$(langListLocator);
    private final SelenideElement currencyName = $(currencyNameLocator);
    private final ElementsCollection tableRatesList = $$(tableRatesListLocator);
}
