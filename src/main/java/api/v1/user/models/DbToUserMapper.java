package api.v1.user.models;

import org.mapstruct.Mapper;

@Mapper
public interface DbToUserMapper {
    User dtoDbToUser(UserDB userDb);
}
