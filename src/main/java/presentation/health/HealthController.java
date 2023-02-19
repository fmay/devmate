package presentation.health;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.health.HealthService;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthController {
    private static Logger logger;
    public HealthController(Javalin app) {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("Initialised");
        app.routes(() -> path("health", () -> {
            path("ping", () -> get(this::ping));
            path("database", () -> get(this::database));
        }));
    }

    public void ping(Context context) {
        logger.debug("ping()");
        context.result("pong");
    }

    public void database(Context context) {
        logger.debug("database()");
        String result = HealthService.database();
        context.json(result);
        logger.debug("DB Info : " + result);
    }

}
