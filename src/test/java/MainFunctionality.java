import core.SelenideListener;
import core.enums.Country;
import core.enums.Pages;
import io.qameta.allure.Attachment;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.currency_calculator.CurrencyCalc;
import pages.currency_calculator.CurrencyCalcElements;
import pages.currency_calculator.FooterCountries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pages.currency_calculator.Utility.*;

@Listeners(SelenideListener.class)
public class MainFunctionality extends BaseTest {

    private final Pages page = Pages.CALCULATOR;
    private final Country country = Country.LT;
    private CurrencyCalc calc;
    private CurrencyCalcElements el;

    @BeforeClass(alwaysRun = true)
    @Override
    protected void beforeClass() {
        prepare(page, country);
        calc = new CurrencyCalc();
        el = new CurrencyCalcElements();
    }

    @TmsLink("1")
    @Attachment(type = "image/png")
    @Test
    public void testBuyAndSellInput() {
        calc.clickSeeRatesButton();
        calc.scrollToSellInput();
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getSellInput(),
                el.getValueScript())), 100);
        calc.enterValuesIntoBuyInput();
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getSellInput(), el.getValueScript())), 0);
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getBuyInput(), el.getValueScript())),
                stringToIntWrapper(el.getSomeValue()));
        calc.enterValuesIntoSellInput();
        Assert.assertEquals(stringToIntWrapper(extractDOMProperties(el.getBuyInput(), el.getValueScript())), 0);
    }

    @TmsLink("2")
    @Attachment(type = "image/png")
    @Test
    public void testChangeLanguageRate() {
        List<String> list = new ArrayList<>(calc.collectCurrencyOptions());
        int index = 0;
        for (FooterCountries dir : FooterCountries.values()) {
            Assert.assertEquals(list.get(index), dir.getCurrency());
            index++;
        }
    }

    @TmsLink("2")
    @Attachment(type = "image/png")
    @Test
    public void testUpdateRatesRows() {
        Map<Integer, Map<Integer, List<Double>>> collectionOfRatesValues = calc.collectRatesRows();
        for (int index = 0; index < collectionOfRatesValues.size(); index++) {
            Assert.assertNotEquals(collectionOfRatesValues.get(index),
                    collectionOfRatesValues.get(index + 1));
        }
    }

    @TmsLink("3")
    @Attachment(type = "image/png")
    @Test
    public void testLowerRates() {
        Map<Integer, List<Double>> mapForAssert = calc.collectRatesRowsAmounts();
        for (int x = 0; x < 10; x++) {
            List<Double> list = mapForAssert.get(x);
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
