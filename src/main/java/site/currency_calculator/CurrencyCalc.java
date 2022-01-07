package site.currency_calculator;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static site.currency_calculator.Warp.*;

public class CurrencyCalc {

    CurrencyCalcElements el = new CurrencyCalcElements();

    @Step
    public void checkSeeRatesButton() {
        moveAndClick(el.getSeeRatesButton());
        try {
            el.getLazyLoaderImage().shouldHave(attribute("class", "text-center ng-hide"));
        } catch (ElementNotFound e) {
            refresh();
            el.getLazyLoaderImage().shouldHave(attribute("class", "text-center ng-hide"));
        }
    }

    @Step
    public void checkInputs() {
        scrollToElement(el.getSellInput());
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getSellInput(), el.getValueScript())), 100);

        moveAndClick(el.getBuyInput());
        sendKeys(el.getSomeValue());
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getSellInput(), el.getValueScript())), 0);
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getBuyInput(), el.getValueScript())),
                stringToIntWrapper(el.getSomeValue()));

        moveAndClick(el.getSellInput());
        sendKeys(el.getSomeValue());
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getBuyInput(), el.getValueScript())), 0);
    }

    @Step
    public void checkCountrySelections() {
        int counter = 0;
        Map<Integer, Map<Integer, List<Double>>> collectForPrevious = new HashMap<>();
        Map<Integer, List<Double>> collections = new HashMap<>();
        for (CurrencyCalcElements.FooterCountries dir : CurrencyCalcElements.FooterCountries.values()) {
            checkSeeRatesButton();
            moveAndClick(el.getLangFooterButton());
            moveAndClick(el.getLangChangeButton());
            moveAndClick($(By.xpath(el.getForDynamicLangElements() + "\"" + dir.getCountry() + "\"" + ")]")));
            checkSeeRatesButton();
            Assert.assertEquals(el.getCurrencyName().getText(), dir.getCurrency());

            for (int x = 0; x < 31; x++) {
                List<Double> list = new ArrayList<>();
                parsePropOuterText(el.getTableRatesList(), el.getOuterTextScript(), list, x);
                collections.put(x, list);
            }
            collectForPrevious.put(counter, collections);

            if (counter > 0) {
                int prevIndex = counter - 1;
                Assert.assertNotEquals(collectForPrevious.get(prevIndex),
                        collectForPrevious.get(counter));
            }
            counter++;
        }
    }

    @Step
    public void checkLowestRates() {
        checkSeeRatesButton();
        moveAndClick(el.getLangFooterButton());
        moveAndClick(el.getLangChangeButton());
        moveAndClick($(By.xpath(el.getForDynamicLangElements() + "\""
                + CurrencyCalcElements.FooterCountries.Lithuania.getCountry() + "\"" + ")]")));
        checkSeeRatesButton();
        for (int x = 0; x < 30; x++) {
            List<Double> list = new ArrayList<>();
            parsePropOuterText(el.getTableRatesList(), el.getOuterTextScript(), list, x);
            if (list.size() < 11) {
                for (int q = list.size(); q < 11; q++) {
                    try {
                        if (list.get(4) == 0) {
                            list.add(5, 0.00);
                        } else if (list.get(8) == 0) {
                            list.add(8, 0.00);
                        } else if (list.get(10) == 0) {
                            list.add(10, 0.00);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        list.add(0.00);
                    }
                }
            }
            System.out.println(list);
            double amountPaysera = list.get(3);
            double amountSwedbank = list.get(4);
            double amountCitadele = list.get(7);
            double amountLuminor = list.get(9);
            double diffBetweenPaysSwed = list.get(5);
            double diffBetweenPaysCitadele = list.get(8);
            double diffBetweenPaysLuminor = list.get(10);
            if (amountPaysera > amountSwedbank && amountSwedbank != 0) {
                Assert.assertEquals(difference(amountSwedbank, amountPaysera), diffBetweenPaysSwed);
            } else if (amountPaysera > amountCitadele && amountCitadele != 0) {
                Assert.assertEquals(difference(amountCitadele, amountPaysera), diffBetweenPaysCitadele);
            } else if (amountPaysera > amountLuminor && amountLuminor != 0) {
                Assert.assertEquals(difference(amountLuminor, amountPaysera), diffBetweenPaysLuminor);
            } else {
                continue;
            }
        }
    }
}
