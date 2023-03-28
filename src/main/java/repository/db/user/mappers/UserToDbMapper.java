package repository.db.user.mappers;

import domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserToDbMapper {
    UserDB userToDbDto(User user);
}
