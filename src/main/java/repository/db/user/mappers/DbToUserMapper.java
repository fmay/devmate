package repository.db.user.mappers;

import domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface DbToUserMapper {
    User dtoDbToUser(UserDB userDb);
}
