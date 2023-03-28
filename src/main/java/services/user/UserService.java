package services.user;

import com.google.gson.Gson;
import com.google.inject.Inject;
import domain.user.User;
import jakarta.validation.ValidationException;
import repository.db.user.IGetUserRepository;
import repository.db.user.IGetAllUsersRepository;
import services.logging.ILogging;

import java.util.List;

public class UserService implements IUserService {
    private final ILogging _logger;
    private final IGetUserRepository _getUserRepo;
    private final IGetAllUsersRepository _getAllUsersRepo;

    @Inject
    UserService(
        ILogging logger,
        IGetUserRepository getUserRepo,
        IGetAllUsersRepository getAllUsersRepo
    ) {
        _logger = logger;
        _getUserRepo = getUserRepo;
        _getAllUsersRepo = getAllUsersRepo;
    }

    @Override
    public String getUser(String id) {
        _logger.info(String.format("getUser(%s)", id));
        if(id.length() < 10) {
            throw new ValidationException("Id must be at least 10 characters long");
        }
        User user = _getUserRepo.execute(id);
        String json = new Gson().toJson(user);
        _logger.debug("User " + user.getUid() + " ");
        return json;
    }

    @Override
    public String getAllUsers() {
        List<User> users = _getAllUsersRepo.execute();
        String json = new Gson().toJson(users);
        _logger.debug("Got " + users.size() + " users");
        return json;
    }
}
