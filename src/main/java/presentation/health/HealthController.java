package presentation.health;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import services.health.IHealthService;
import services.logging.ILogging;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthController implements IHealthController {
    private static ILogging _logger;
    private IHealthService _healthService;

    @Inject
    public HealthController(IHealthService healthService, ILogging logger) {
        _logger = logger;
        _logger.info("Health controller initialised");
        _healthService  = healthService;
    }

    public void init(Javalin app) {
        app.routes(() -> path("health", () -> {
            path("ping", () -> get(this::ping));
            path("database", () -> get(this::database));
        }));
    }

    public void ping(Context context) {
        _logger.debug("ping()");
        context.result("pong");
    }

    public void database(Context context) {
        _logger.debug("database()");
        String result = _healthService.databaseInfo();
        context.json(result);
        _logger.debug("DB Info : " + result);
    }

}
