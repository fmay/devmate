package presentation.health;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.db.health.Version;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthController {
    private static Logger logger;
    public HealthController(Javalin app) {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("Initialised");
        app.routes(() -> path("health", () -> {
            path("ping", () -> get(this::ping));
            path("neo4j", () -> get(this::neo4j));
        }));
    }

    public void ping(Context context) {
        logger.debug("ping()");
        context.result("pong");
    }

    public void neo4j(Context context) throws JsonProcessingException {
        logger.debug("neo4j()");
        var result = Version.execute();
        context.json(new ObjectMapper().writeValueAsString(result));
        logger.debug("Version : " + result);
    }

}
