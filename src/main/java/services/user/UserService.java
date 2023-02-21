package services.user;

import com.google.gson.Gson;
import domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.db.user.GetUserRepository;
import repository.db.user.GetUsersRepository;

import java.util.List;

public class UserService {
    static Logger logger = LoggerFactory.getLogger(UserService.class.getName());
    static final GetUsersRepository getUsersRepository = new GetUsersRepository();
    static final GetUserRepository getUserRepository = new GetUserRepository();

    public String getUser(String id) {
        logger.info("getUser()");

        User user = this.getUserRepository.execute(id);
        String json = new Gson().toJson(user);

        logger.debug("User " + user.getUid() + " " +
                user.getProfile().contact_first_name() + " " +
                user.getProfile().contact_last_name());
        return json;
    }

    public String getUsers() {
        List<User> users = getUsersRepository.execute();
        String json = new Gson().toJson(users);
        logger.debug("Got " + users.size() + " users");
        return json;
    }
}
