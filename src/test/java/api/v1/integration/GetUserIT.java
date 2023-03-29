package api.v1.integration;

import api.v1.core.config.Config;
import api.v1.core.database.ISystemDatabase;
import api.v1.core.database.Neo4j;
import api.v1.core.logging.ILogging;
import api.v1.user.models.User;
import api.v1.user.repository.GetAllUsersRepository;
import api.v1.user.repository.GetUserRepository;
import api.v1.user.repository.IGetAllUsersRepository;
import api.v1.user.repository.IGetUserRepository;
import api.v1.user.service.IUserService;
import api.v1.user.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GetUserIT {

    private ILogging logger;
    private Config config;
    private IGetUserRepository getUserRepository;

    @BeforeEach
    void setUp() {
        config = new Config();
        logger = mock(ILogging.class);
        ISystemDatabase db = new Neo4j(config);
        getUserRepository = new GetUserRepository(db, logger);
    }

    @Test
    void executeRepo() {
        User user = getUserRepository.execute("b8Pcx8ZidDuNevzocglxM9SxhpIs");
        assertEquals(user.getUid(), "b8Pcx8ZidDuNevzocglxM9SxhpIs");
    }

    @Test
    void executeService() {
        IGetAllUsersRepository mockAll = mock(IGetAllUsersRepository.class);
        UserService userService = new UserService(logger, getUserRepository, mockAll);
        String json = userService.getUser("b8Pcx8ZidDuNevzocglxM9SxhpIs");
        User user = new Gson().fromJson(json, User.class);
        assertEquals(user.getUid(), "b8Pcx8ZidDuNevzocglxM9SxhpIs");
    }


}
