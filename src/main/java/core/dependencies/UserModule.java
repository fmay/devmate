package core.dependencies;

import com.google.inject.AbstractModule;
import presentation.user.IUserController;
import presentation.user.UserController;
import repository.db.user.GetUserRepository;
import repository.db.user.GetAllUsersRepository;
import repository.db.user.IGetUserRepository;
import repository.db.user.IGetAllUsersRepository;
import services.user.IUserService;
import services.user.UserService;

public class UserModule extends AbstractModule {

    protected void configure() {
        bind(IUserController.class).to(UserController.class);
        bind(IUserService.class).to(UserService.class);
        bind(IGetUserRepository.class).to(GetUserRepository.class);
        bind(IGetAllUsersRepository.class).to(GetAllUsersRepository.class);
    }

}
