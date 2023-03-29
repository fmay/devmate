package api.v1.user.repository;

import api.v1.user.models.User;

public interface IGetUserRepository {
    User execute(String loggedInUserId);
}
