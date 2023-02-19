package presentation.user;

import com.google.gson.Gson;
import domain.user.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import repository.db.user.GetUser;
import repository.db.user.GetUsers;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController {
    static String[] tempUsers = {"User1", "User2", "User3"};
    public UserController(Javalin app) {
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
        List<User> users = new GetUsers().execute();
        String json = new Gson().toJson(users);
        context.json(json);
    }

    public static void getUser(Context context) {
        String id = context.queryParamAsClass("id", String.class).get();
        User user = new GetUser().execute(id);
        context.json(user);
    }

    public static void updateUser(Context context) {
        int index = Integer.parseInt(context.pathParam("id"));
        context.json(tempUsers[index]);
    }
}
