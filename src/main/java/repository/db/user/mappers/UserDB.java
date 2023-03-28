package repository.db.user.mappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import domain.user.UserType;

@JsonIgnoreProperties(ignoreUnknown=true)
public record UserDB(
    @JsonProperty("uid") String uid,
    @JsonProperty("userType") UserType userType,
    @JsonProperty("hasAvatar") boolean hasAvatar,
    @JsonProperty("hasBanner") boolean hasBanner) {
}
