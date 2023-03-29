package org.example;
import api.v1.core.modules.Dependencies;
import io.javalin.Javalin;
import api.v1.core.logging.ILogging;

public class Application {

    public static void main(String[] args) {

        // Initialise Javalin
        Javalin javalin = Javalin.create();
        javalin.start(7070);

        // Inject Dependencies
        ILogging logger = Dependencies.init(javalin);

        // Global exception handler
        javalin.exception(Exception.class, (e, ctx) -> {
            logger.error("Exception : "  + e);
            ctx.status(404);
            ctx.result(e.getMessage());
        });

    }

}