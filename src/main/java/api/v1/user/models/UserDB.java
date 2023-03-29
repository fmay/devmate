package api.v1.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record UserDB(
    @JsonProperty("uid") String uid,
    @JsonProperty("userType") UserType userType,
    @JsonProperty("hasAvatar") boolean hasAvatar,
    @JsonProperty("hasBanner") boolean hasBanner) {
}
