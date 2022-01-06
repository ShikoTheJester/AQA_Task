package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class DriverConfiguration {

    private static DesiredCapabilities defaultRemote() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability("name", "TestSite");
        capabilities.setCapability("sessionTimeout", "5m");
        capabilities.setCapability("screenResolution", "2000x1100x24");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--start-maximized");
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    static DesiredCapabilities getCapabilities() {
        return defaultRemote();
    }
}
