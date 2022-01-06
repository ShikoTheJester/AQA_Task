import com.codeborne.selenide.testng.ScreenShooter;
import core.BaseTest;
import core.enums.Country;
import core.enums.Pages;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import site.currency_calculator.CurrencyCalc;

@Listeners({ScreenShooter.class})
public class MainFunctionality extends BaseTest {

    private final Pages page = Pages.CALCULATOR;
    private final Country country = Country.LT;
    private CurrencyCalc calc;

    @BeforeClass(alwaysRun = true)
    @Override
    protected void beforeClass() {
        prepare(page, country);
        calc = new CurrencyCalc();
    }

    @TmsLink("1")
    @Test
    public void testBuyAndSellInput() {
        calc.checkSeeRatesButton();
        calc.checkInputs();
    }

    @TmsLink("2")
    @Test
    public void testChangeLanguage() {
        calc.checkCountrySelections();
    }

    @TmsLink("3")
    @Test
    public void testLowerRates() {
        calc.checkLowestRates();
    }
}
