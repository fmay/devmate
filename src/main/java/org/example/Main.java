package org.example;
import domain.application.Config;
import presentation.health.HealthController;
import presentation.user.UserController;
import io.javalin.Javalin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("org.example.Main");
        logger.info("App started");

        // Initialise and create Configuration
        new Config();

        // Initialise Javalin
        Javalin app = Javalin.create();
        app.start(7070);

        // Global exception handler
        app.exception(Exception.class, (e, ctx) -> logger.error("Uncaught exception : "  + e));

        // Controllers
        new UserController(app);
        new HealthController(app);
    }

}