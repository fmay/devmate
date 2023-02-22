package presentation.finder;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.finder.FinderService;

import java.io.IOException;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class FinderController {

    private static Logger logger;
    private static FinderService finderService;

    public FinderController(Javalin app) {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("Initialised");
        finderService = new FinderService();
        app.routes(() -> path("finder", () -> {
            // Get one user
            post(this::runQuery);
        }));
    }

    private void runQuery(Context ctx) throws IOException {
        String json = finderService.runQuery(ctx.body());
        ctx.result(json);
    }
}
