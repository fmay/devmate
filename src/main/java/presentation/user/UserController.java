package presentation.user;

import domain.user.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.user.UserService;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController {
    private static Logger logger;
    private static UserService userService;

    public UserController(Javalin app) {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("Initialised");
        userService = new UserService();
        app.routes(() -> path("user", () -> {
            // Get all users
            path("list", () -> get(this::getUsers));

            // Get one user
            get(this::getUser);

            // Update a user
            path("{id}", () -> put(this::updateUser));
        }));
    }

    public void getUsers(Context context) {
        logger.debug("getUsers()");
        context.json(userService.getUsers());
    }

    public void getUser(Context context) {
        logger.debug("getUser()");
        String id = context.queryParamAsClass("id", String.class).get();
        context.json(userService.getUser(id));
    }

    public void updateUser(Context context) {
    }
}
