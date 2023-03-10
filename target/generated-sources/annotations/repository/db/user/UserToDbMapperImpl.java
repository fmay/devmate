package repository.db.user;

import domain.user.User;
import domain.user.UserType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-22T12:44:02+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class UserToDbMapperImpl implements UserToDbMapper {

    @Override
    public UserDB userToDbDto(User user) {
        if ( user == null ) {
            return null;
        }

        String uid = null;
        UserType userType = null;
        boolean hasAvatar = false;
        boolean hasBanner = false;

        uid = user.getUid();
        userType = user.getUserType();
        hasAvatar = user.isHasAvatar();
        hasBanner = user.isHasBanner();

        UserDB userDB = new UserDB( uid, userType, hasAvatar, hasBanner );

        return userDB;
    }
}
