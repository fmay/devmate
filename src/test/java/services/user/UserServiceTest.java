package services.user;

import com.google.gson.Gson;
import domain.user.Profile;
import domain.user.User;
import domain.user.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import repository.db.user.GetUserRepository;
import repository.db.user.GetAllUsersRepository;
import repository.db.user.IGetAllUsersRepository;
import repository.db.user.IGetUserRepository;
import services.logging.ILogging;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private IGetUserRepository mockGetUserRepo;
    private IGetAllUsersRepository mockGetAllUsersRepo;
    private ILogging mockLogging;
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockLogging = mock(ILogging.class);
        mockGetAllUsersRepo = mock(IGetAllUsersRepository.class);
        mockGetUserRepo = mock(IGetUserRepository.class);
        userService = new UserService(mockLogging, mockGetUserRepo, mockGetAllUsersRepo);
    }

    @Test
    void getUser() {
        User testUser = new User("b8Pcx8ZidDuNevzocglxM9SxhpIs", UserType.PERSON, false, false);
        String expectedUser = new Gson().toJson(testUser);
        when(mockGetUserRepo.execute(anyString())).thenReturn(testUser);
        String resultUser = userService.getUser("b8Pcx8ZidDuNevzocglxM9SxhpIs");
        assertEquals(expectedUser, resultUser);
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("aaaaaaaaaaaaa", UserType.PERSON, false, false));
        users.add(new User("bbbbbbbbbbbbb", UserType.ORGANISATION, true, true));
        when(mockGetAllUsersRepo.execute()).thenReturn(users);
        String resultUser = userService.getAllUsers();
        assertEquals(new Gson().toJson(users), resultUser);
    }
}