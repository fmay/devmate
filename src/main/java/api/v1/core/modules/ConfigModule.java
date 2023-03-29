package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.core.config.Config;
import api.v1.core.config.IConfig;

public class ConfigModule extends AbstractModule {

    protected void configure() {
        bind(IConfig.class).to(Config.class);
    }

}
