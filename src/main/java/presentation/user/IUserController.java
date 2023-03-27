package presentation.user;

import io.javalin.Javalin;
import io.javalin.http.Context;

public interface IUserController {
    void init(Javalin javalin);

    void getAllUsers(Context context);

    void getUser(Context context);

    void updateUser(Context context);
}
