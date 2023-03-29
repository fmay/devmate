package api.v1.user.mappers;

import api.v1.user.models.User;
import api.v1.user.models.UserType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-29T12:49:58+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class DbToUserMapperImpl implements DbToUserMapper {

    @Override
    public User dtoDbToUser(UserSummaryData userDb) {
        if ( userDb == null ) {
            return null;
        }

        String uid = null;
        UserType userType = null;
        boolean hasAvatar = false;
        boolean hasBanner = false;

        uid = userDb.uid();
        userType = userDb.userType();
        hasAvatar = userDb.hasAvatar();
        hasBanner = userDb.hasBanner();

        User user = new User( uid, userType, hasAvatar, hasBanner );

        return user;
    }
}
