package presentation.health;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.LoggerFactory;
import services.health.IHealthService;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public interface IHealthController {

    void init(Javalin javalin);

    void ping(Context context);

    void database(Context context);
}
