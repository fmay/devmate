package repository.db.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.user.UserType;

@JsonIgnoreProperties(ignoreUnknown=true)
public record UserDB(String uid, UserType userType, boolean hasAvatar, boolean hasBanner) {

}
