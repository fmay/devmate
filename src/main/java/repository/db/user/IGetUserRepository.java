package repository.db.user;

import domain.user.User;

public interface IGetUserRepository {
    User execute(String loggedInUserId);
}
