package tests;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import config.RemoteDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {
    private static final DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());
    private static final RemoteDriverConfig remoteDriverConfig = ConfigFactory.create(RemoteDriverConfig.class);

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = config.getBrowser();
        if (config.getRemoteDriver() != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = String.format(config.getRemoteDriver(),
                    remoteDriverConfig.getUser(),
                    remoteDriverConfig.getPassword());
        }
        Configuration.browserSize = "1024x768";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        if (Configuration.browser.equals("chrome"))
            attachAsText("Browser console logs", getConsoleLogs());
        if (config.getVideoStorage() != null)
            attachVideo(config.getVideoStorage());
        closeWebDriver();
    }
}
