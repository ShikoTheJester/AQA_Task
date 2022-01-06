package core;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class Navigation {
    private static final String BASE = System.getProperty("application.url", "https://www.paysera.lt");
    private static final String VERSION = "/v2";
    private static final String LANGUAGE = "/en-LT";
    private static final String FEES = "/fees";

    @Step
    public static void toHomePage() {
        open(BASE);
    }

    @Step
    public static void toCalculator() {
        open(BASE + VERSION + LANGUAGE + FEES + "/currency-conversion-calculator#");
    }
}
