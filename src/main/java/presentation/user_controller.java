package presentation;

import io.javalin.Javalin;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class user_controller {

    static String[] tempUsers = {"User1", "User2", "User3"};
    public user_controller(Javalin app) {
        app.routes(() -> {
            path("users", () -> {
                get(user_controller::getUsers);
                path("{id}", () -> {
                    get(user_controller::getUser);
                });
            });
        });
    }

    public static void getUsers(Context context) {
        context.json(tempUsers);
    }

    public static void getUser(Context context) {
        int index = Integer.parseInt(context.pathParam("id"));
        context.json(tempUsers[index]);
    }
}
