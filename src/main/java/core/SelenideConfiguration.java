package core;

import com.codeborne.selenide.Configuration;
import core.enums.Country;
import core.enums.Resolution;

import static com.codeborne.selenide.Browsers.CHROME;

public class SelenideConfiguration {

    private static Resolution resolution = Resolution.DESKTOP;
    private static Country country = Country.LT;


    public static void configure(Resolution resolution, Country country) {
        SelenideConfiguration.resolution = resolution;
        SelenideConfiguration.country = country;
        doAll();
    }

    private static void doAll() {
        doBasic();
        doRemote();
        doResolution();
    }

    private static void doBasic() {
        Configuration.browser = CHROME;
        Configuration.headless = Boolean.parseBoolean(PropertiesReader.get("headlessMode"));
        Configuration.fastSetValue = true;
        Configuration.savePageSource = false;
        Configuration.timeout = 15000;
        Configuration.reportsFolder = "target/allure-results";
    }

    private static void doRemote() {
        if (!Boolean.parseBoolean(PropertiesReader.get("local.launch"))) {
            Configuration.remote = PropertiesReader.get("selenoid.url");
            Configuration.browserCapabilities = DriverConfiguration.getCapabilities();
        }
    }

    private static void doResolution() {
        switch (resolution) {
            case DESKTOP:
                Configuration.browserSize = (PropertiesReader.get("local.launch").equals("true")) ? "1920x1080" : Resolution.DESKTOP.resolution();
                Configuration.startMaximized = true;
                break;
            case TABLET:
                Configuration.browserSize = Resolution.TABLET.resolution();
                break;
            default:
                throw new IllegalArgumentException("Unsupported resolution");
        }
    }
}
