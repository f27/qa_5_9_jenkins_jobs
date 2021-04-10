package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:config/driver.properties" })
public interface RemoteDriverConfig extends Config {
    @Key("user")
    String getUser();

    @Key("password")
    String getPassword();
}
