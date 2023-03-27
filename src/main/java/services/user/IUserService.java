package services.user;

import repository.db.user.IGetUserRepository;

public interface IUserService {
    String getUser(String id);

    String getAllUsers();
}
