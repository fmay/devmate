package domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Profile(
        String userId,
        String contact_email,
        String contact_first_name,
        String contact_last_name,
        String contact_organisation_name,
        String contact_phone,
        String contact_public_email,
        String contact_whatsapp,
        String contact_skills_string,
        String user_bio
        ) {
}
