package repository.db.user;

import domain.user.User;
import domain.user.UserType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-22T12:44:02+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class DbToUserMapperImpl implements DbToUserMapper {

    @Override
    public User dbToUserDto(UserDB userDb) {
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
