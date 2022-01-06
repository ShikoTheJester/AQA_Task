package site.currency_calculator;

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

    private final SelenideElement rateTable = $(RATE_TABLE);
    private final SelenideElement seeRatesButton = $(SEE_RATES_BUTTON);
    private final SelenideElement currencyBlock = $(CURRENCY_BLOCK);
    private final SelenideElement sellInput = $(SELL_INPUT);
    private final SelenideElement buyInput = $(BUY_INPUT);
    private final SelenideElement lazyLoaderImage = $(LAZY_LOADER_IMAGE);
    private final SelenideElement langFooterButton = $(LANG_FOOTER_BUTTON);
    private final SelenideElement langChangeButton = $(LANG_CHANGE_BUTTON);
    private final ElementsCollection langList = $$(LANG_LIST);
    private final SelenideElement currencyName = $(CURRENCY_NAME);
    private final ElementsCollection tableRatesList = $$(TABLE_RATES_LIST);

    @Getter
    enum FooterCountries {
        Lithuania("Lithuania","EUR"),
        Latvia("Latvia","EUR"),
        Estonia("Estonia","EUR"),
        Bulgaria("Bulgaria","BGN"),
        Spain("Spain","EUR"),
        Romania("Romania","RON"),
        Poland("Poland","PLN"),
        United("United Kingdom","GBP"),
        Germany("Germany","EUR"),
        Russia("Russia","RUB"),
        Algeria("Algeria","DZD"),
        Albania("Albania","ALL"),
        Kosovo("Kosovo","EUR"),
        Ukraine("Ukraine","UAH"),
        Another("Another","EUR");

        String country;
        String currency;

        FooterCountries(String country, String currency) {
            this.currency = currency;
            this.country = country;
        }
    }
}
