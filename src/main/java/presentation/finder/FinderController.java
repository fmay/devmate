package presentation.finder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.finder.FinderQueryDTO;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.finder.FinderService;

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

    private void runQuery(Context ctx) throws JsonProcessingException {
        // Convert body to FinderQuery
        ObjectMapper mapper = new ObjectMapper();
        FinderQueryDTO query = mapper.readValue(ctx.body(), FinderQueryDTO.class);
        finderService.runQuery(query);
        ctx.result("AAAA");
    }
}
