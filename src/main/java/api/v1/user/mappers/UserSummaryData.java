package api.v1.user.mappers;

import api.v1.user.models.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record UserSummaryData(
    @JsonProperty("uid") String uid,
    @JsonProperty("userType") UserType userType,
    @JsonProperty("hasAvatar") boolean hasAvatar,
    @JsonProperty("hasBanner") boolean hasBanner) {
}
