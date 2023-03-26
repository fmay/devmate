package core.dependencies;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Dependencies {

    public static Injector init() {
        Injector injector = Guice.createInjector(
            new LoggingModule(),
            new HealthModule()
        );
        return injector;
    }

}
