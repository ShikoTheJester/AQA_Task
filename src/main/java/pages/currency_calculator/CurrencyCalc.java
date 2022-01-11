package pages.currency_calculator;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.currency_calculator.Utility.*;

public class CurrencyCalc {

    CurrencyCalcElements el = new CurrencyCalcElements();

    @Step
    public void clickSeeRatesButton() {
        try {
            moveAndClick(el.getSeeRatesButton());
            el.getLazyLoaderImage().shouldHave(attribute("class", "text-center ng-hide"));
        } catch (ElementNotFound e) {
            refresh();
            el.getLazyLoaderImage().shouldHave(attribute("class", "text-center ng-hide"));
        }
    }

    @Step
    public void scrollToSellInput() {
        scrollToElement(el.getSellInput());
    }

    @Step
    public void enterValuesIntoBuyInput() {
        moveAndClick(el.getBuyInput());
        sendKeys(el.getSomeValue());
    }

    @Step
    public void enterValuesIntoSellInput() {
        moveAndClick(el.getSellInput());
        sendKeys(el.getSomeValue());
    }

    @Step
    public List<String> collectCurrencyOptions() {
        List<String> list = new ArrayList<>();
        for (FooterCountries dir : FooterCountries.values()) {
            clickSeeRatesButton();
            moveAndClick(el.getLangFooterButton());
            moveAndClick(el.getLangChangeButton());
            moveAndClick($(By.xpath(el.getForDynamicLangElements() + "\"" + dir.getCountry() + "\"" + ")]")));
            clickSeeRatesButton();
            list.add(el.getCurrencyName().getText());
        }
        return list;
    }

    @Step
    public Map<Integer, Map<Integer, List<Double>>> collectRatesRows() {
        int counter = 0;
        Map<Integer, Map<Integer, List<Double>>> collectForPrevious = new HashMap<>();
        HashMap<Integer, List<Double>> collections = new HashMap<>();
        Map<Integer, List<Double>> copyOfCollections;

        for (FooterCountries dir : FooterCountries.values()) {
            clickSeeRatesButton();
            moveAndClick(el.getLangFooterButton());
            moveAndClick(el.getLangChangeButton());
            moveAndClick($(By.xpath(el.getForDynamicLangElements() + "\"" + dir.getCountry() + "\"" + ")]")));
            clickSeeRatesButton();
            for (int x = 0; x < 3; x++) {
                List<Double> list = new ArrayList<>();
                parsePropOuterText(el.getTableRatesList(), el.getOuterTextScript(), list, x);
                collections.put(x, list);
            }
            copyOfCollections = (Map<Integer, List<Double>>) collections.clone();
            collectForPrevious.put(counter, copyOfCollections);
            counter++;
        }
        return collectForPrevious;
    }

    @Step
    public Map<Integer, List<Double>> collectRatesRowsAmounts() {
        clickSeeRatesButton();
        moveAndClick(el.getLangFooterButton());
        moveAndClick(el.getLangChangeButton());
        moveAndClick($(By.xpath(el.getForDynamicLangElements() + "\""
                + FooterCountries.Lithuania.getCountry() + "\"" + ")]")));
        clickSeeRatesButton();
        List<Double> rowAmounts = new ArrayList<>();
        Map<Integer, List<Double>> mapForAssert = new HashMap<>();
        for (int x = 0; x < 10; x++) {
            parsePropOuterText(el.getTableRatesList(), el.getOuterTextScript(), rowAmounts, x);
            if (rowAmounts.size() < 11) {
                for (int q = rowAmounts.size(); q < 11; q++) {
                    try {
                        if (rowAmounts.get(4) == 0) {
                            rowAmounts.add(5, 0.00);
                        } else if (rowAmounts.get(8) == 0) {
                            rowAmounts.add(8, 0.00);
                        } else if (rowAmounts.get(10) == 0) {
                            rowAmounts.add(10, 0.00);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        rowAmounts.add(0.00);
                    }
                }
            }
            mapForAssert.put(x, rowAmounts);
        }
        return mapForAssert;
    }
}
