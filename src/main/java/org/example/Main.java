package org.example;
import core.config.Config;
import core.dependencies.Dependencies;
import presentation.finder.FinderController;
import presentation.health.HealthController;
import presentation.health.IHealthController;
import presentation.user.UserController;
import io.javalin.Javalin;
import com.google.inject.Injector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.logging.ILogging;

public class Main {

    public static void main(String[] args) {
        // Dependency Injection
        Injector injector = Dependencies.init();


        ILogging logger = injector.getInstance(ILogging.class);
        logger.error("Just testing");

        // Initialise Javalin
        Javalin javalin = Javalin.create();
        javalin.start(7070);

        // Initialise and create Configuration
        new Config();

        // Global exception handler
        javalin.exception(Exception.class, (e, ctx) -> {
            logger.error("Uncaught exception : "  + e);
            ctx.status(404);
            ctx.result(e.getMessage());
        });

        // DI for Controllers and initialise
        IHealthController _healthController = injector.getInstance(IHealthController.class);
        _healthController.init(javalin);

        // Controllers
        new UserController(javalin);
        new FinderController(javalin);
//        new HealthController(app);

    }

}