package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.user.controller.IUserController;
import api.v1.user.controller.UserController;
import api.v1.user.repository.GetUserRepository;
import api.v1.user.repository.GetAllUsersRepository;
import api.v1.user.repository.IGetUserRepository;
import api.v1.user.repository.IGetAllUsersRepository;
import api.v1.user.service.IUserService;
import api.v1.user.service.UserService;

public class UserModule extends AbstractModule {

    protected void configure() {
        bind(IUserController.class).to(UserController.class);
        bind(IUserService.class).to(UserService.class);
        bind(IGetUserRepository.class).to(GetUserRepository.class);
        bind(IGetAllUsersRepository.class).to(GetAllUsersRepository.class);
    }

}
