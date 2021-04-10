package config;

import org.aeonbits.owner.Config;

public interface DriverConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("remote.driver")
    String getRemoteDriver();

    @Key("video.storage")
    String getVideoStorage();
}
