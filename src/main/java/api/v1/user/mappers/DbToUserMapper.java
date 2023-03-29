package api.v1.user.mappers;

import api.v1.user.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface DbToUserMapper {
    User dtoDbToUser(UserSummaryData userDb);
}
