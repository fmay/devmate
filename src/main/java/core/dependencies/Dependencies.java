package core.dependencies;

import com.google.inject.Guice;
import com.google.inject.Injector;
import core.config.IConfig;
import core.config.ISystemDatabase;
import io.javalin.Javalin;
import presentation.finder.IFinderController;
import presentation.health.IHealthController;
import presentation.user.IUserController;
import services.logging.ILogging;

public class Dependencies {

    public static ILogging init(Javalin javalin) {

        Injector injector = Guice.createInjector(
            new ConfigModule(),
            new LoggingModule(),
            new DatabaseModule(),
            new HealthModule(),
            new UserModule(),
            new FinderModule()
        );

        // Logging
        ILogging logger = injector.getInstance(ILogging.class);
        logger.info("Dependencies injected");

        // Config
        injector.getInstance(IConfig.class);

        // System DB
        injector.getInstance(ISystemDatabase.class);

        // Controllers and initialise
        IHealthController _healthController = injector.getInstance(IHealthController.class);
        IUserController _userController = injector.getInstance(IUserController.class);
        IFinderController _finderController = injector.getInstance(IFinderController.class);
        _healthController.init(javalin);
        _userController.init((javalin));
        _finderController.init((javalin));

        return logger;
    }

}
