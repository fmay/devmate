package Presentation;

import io.javalin.http.Context;

public final class UserController {

    static String[] tempUsers = {"User1", "User2", "User3"};
    public UserController() {

    }

    public static void getUsers(Context context) {
        context.json(tempUsers);
    }

    public static void getUser(Context context) {
        int index = Integer.parseInt(context.pathParam("id"));
        context.json(tempUsers[index]);
    }
}
