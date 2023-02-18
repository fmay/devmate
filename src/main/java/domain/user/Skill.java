package domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Skill(String topLevelId, String skillId, Integer level) {
}
