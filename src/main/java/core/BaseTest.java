package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import core.enums.Country;
import core.enums.Pages;
import core.enums.Resolution;
import org.testng.annotations.*;

public abstract class BaseTest {
    protected Pages p;
    protected Country c;

    @BeforeClass(alwaysRun = true)
    protected void beforeClass() {
        prepare(p, c);
    }

    @BeforeMethod(alwaysRun = true)
    protected void beforeTest() {
        Selenide.refresh();
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod() {
    }

    @AfterClass(alwaysRun = true)
    protected void afterClass() {
        Selenide.closeWebDriver();
    }

    protected void prepare(Pages page, Country country) {
        SelenideConfiguration.configure(Resolution.DESKTOP, country);
        openPage(page);
        Configuration.startMaximized = true;
    }

    protected void openPage(Pages page) {
        switch (page) {
            case HOMEPAGE:
                Navigation.toHomePage();
                break;
            case CALCULATOR:
                Navigation.toCalculator();
                break;
        }
    }
}
