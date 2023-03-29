package api.v1.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Profile(
        @JsonProperty("userId") String userId,
        @JsonProperty("contact_email") String contact_email,
        @JsonProperty("contact_first_name") String contact_first_name,
        @JsonProperty("contact_last_name") String contact_last_name,
        @JsonProperty("contact_organisation_name") String contact_organisation_name,
        @JsonProperty("contact_phone") String contact_phone,
        @JsonProperty("contact_public_email") String contact_public_email,
        @JsonProperty("contact_whatsapp") String contact_whatsapp,
        @JsonProperty("user_bio") String user_bio
) {
}
