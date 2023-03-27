package repository.db.user;

import domain.user.User;

import java.util.List;

public interface IGetUsersRepository {
    List<User> execute();
}
