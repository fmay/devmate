package core.dependencies;

import com.google.inject.AbstractModule;
import core.config.Config;
import core.config.IConfig;
import services.logging.ILogging;
import services.logging.Logging;

public class ConfigModule extends AbstractModule {

    protected void configure() {
        bind(IConfig.class).to(Config.class);
    }

}
