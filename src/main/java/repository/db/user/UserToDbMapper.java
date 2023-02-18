package repository.db.user;

import domain.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserToDbMapper {
    UserDB userToDbDto(User user);
}
