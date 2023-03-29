package api.v1.user.repository;

import api.v1.user.models.User;

import java.util.List;

public interface IGetAllUsersRepository {
    List<User> execute();
}
