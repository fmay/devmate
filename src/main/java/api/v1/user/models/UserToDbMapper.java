package api.v1.user.models;

import org.mapstruct.Mapper;

@Mapper
public interface UserToDbMapper {
    UserDB userToDbDto(User user);
}
