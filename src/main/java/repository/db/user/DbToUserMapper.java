package repository.db.user;

import domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface DbToUserMapper {
    User dbToUserDto(UserDB userDb);
}
