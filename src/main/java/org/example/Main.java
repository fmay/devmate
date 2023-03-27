package org.example;
import core.dependencies.Dependencies;
import io.javalin.Javalin;
import services.logging.ILogging;

public class Main {

    public static void main(String[] args) {

        // Initialise Javalin
        Javalin javalin = Javalin.create();
        javalin.start(7070);

        // Inject Dependencies
        ILogging logger = Dependencies.init(javalin);

        // Global exception handler
        javalin.exception(Exception.class, (e, ctx) -> {
            logger.error("Uncaught exception : "  + e);
            ctx.status(404);
            ctx.result(e.getMessage());
        });

    }

}