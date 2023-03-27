package core.dependencies;

import com.google.inject.Guice;
import com.google.inject.Injector;
import core.config.Config;
import io.javalin.Javalin;
import presentation.finder.FinderController;
import presentation.health.IHealthController;
import presentation.user.UserController;
import services.logging.ILogging;

public class Dependencies {

    public static Injector init(Javalin javalin) {

        Injector injector = Guice.createInjector(
            new LoggingModule(),
            new HealthModule()
        );

        ILogging logger = injector.getInstance(ILogging.class);
        logger.error("Just testing");

        // Initialise and create Configuration
        new Config();

        // DI for Controllers and initialise
        IHealthController _healthController = injector.getInstance(IHealthController.class);
        _healthController.init(javalin);

        // Controllers
        new UserController(javalin);
        new FinderController(javalin);
//        new HealthController(app);
        return injector;
    }

}
