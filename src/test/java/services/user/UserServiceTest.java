package services.user;

import api.v1.user.service.UserService;
import com.google.gson.Gson;
import api.v1.user.models.User;
import api.v1.user.models.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import api.v1.user.repository.IGetAllUsersRepository;
import api.v1.user.repository.IGetUserRepository;
import api.v1.core.logging.ILogging;

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