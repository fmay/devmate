package presentation.user;

import com.google.gson.Gson;
import domain.user.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.db.user.GetUser;
import repository.db.user.GetUsers;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController {
    private static Logger logger;
    public UserController(Javalin app) {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("Initialised");
        app.routes(() -> path("user", () -> {
            // Get all users
            path("list", () -> get(UserController::getUsers));

            // Get one user
            get(UserController::getUser);

            // Update a user
            path("{id}", () -> put(UserController::updateUser));
        }));
    }

    public static void getUsers(Context context) {
        logger.debug("getUsers()");
        List<User> users = new GetUsers().execute();
        String json = new Gson().toJson(users);
        logger.debug("Got " + users.size() + " users");
        context.json(json);
    }

    public static void getUser(Context context) {
        logger.debug("getUser()");
        String id = context.queryParamAsClass("id", String.class).get();
        User user = new GetUser().execute(id);
        logger.debug(user.toString());
        context.json(user);
    }

    public static void updateUser(Context context) {
    }
}
