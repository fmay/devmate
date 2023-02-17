package presentation;

import com.google.gson.Gson;
import domain.user.User;
import domain.user.UserDAO;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class user_controller {
    private static UserDAO userDao = new UserDAO();
    static String[] tempUsers = {"User1", "User2", "User3"};
    public user_controller(Javalin app) {
        app.routes(() -> path("users", () -> {
            get(user_controller::getUsers);
            path("{id}", () -> {
                get(user_controller::getUser);
            });
        }));
    }

    public static void getUsers(Context context) {
        List<User> users = userDao.getAll();
        String json = new Gson().toJson(users);
        System.out.println(json);

        context.json(tempUsers);
    }

    public static void getUser(Context context) {
        int index = Integer.parseInt(context.pathParam("id"));
        context.json(tempUsers[index]);
    }
}
