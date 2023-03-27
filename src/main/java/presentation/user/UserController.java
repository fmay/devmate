package presentation.user;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;
import services.logging.ILogging;
import services.user.IUserService;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController implements IUserController {
    private ILogging _logger;
    private IUserService _userService;

    @Inject
    public UserController(IUserService userService, ILogging logger) {
        _logger = logger;
        _userService = userService;
        _logger.info("User Controller initialised");
    }

    @Override
    public void init(Javalin app) {
        app.routes(() -> path("user", () -> {
            path("list", () -> get(this::getAllUsers));
            get(this::getUser);
            path("{id}", () -> put(this::updateUser));
        }));
    }

    @Override
    public void getAllUsers(Context context) {
        _logger.debug("getUsers()");
        context.json(_userService.getAllUsers());
    }

    @Override
    public void getUser(Context context) {
        _logger.debug("getUser()");
        String id = context.queryParamAsClass("id", String.class).get();
        context.json(_userService.getUser(id));
    }

    @Override
    public void updateUser(Context context) {
    }
}
