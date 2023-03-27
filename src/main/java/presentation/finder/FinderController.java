package presentation.finder;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import services.finder.IFinderService;
import services.logging.ILogging;

import java.io.IOException;

import static io.javalin.apibuilder.ApiBuilder.*;

public class FinderController implements IFinderController {
    private final ILogging _logger;
    private final IFinderService _finderService;

    @Inject
    public FinderController(ILogging logger, IFinderService finderService) {
        _logger = logger;
        _finderService = finderService;
        logger.info("Finder controller Initialised");
    }

    @Override
    public void init(Javalin javalin) {
        javalin.routes(() -> path("finder", () -> {
            // Get one user
            post(this::runQuery);
        }));
    }

    @Override
    public void runQuery(Context ctx) throws IOException {
        String json = _finderService.runQuery(ctx.body());
        ctx.result(json);
    }
}
