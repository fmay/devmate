package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.core.logging.ILogging;
import api.v1.core.logging.Logging;

public class LoggingModule extends AbstractModule {

    protected void configure() {
        bind(ILogging.class).to(Logging.class).asEagerSingleton();
    }

}
