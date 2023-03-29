package api.v1.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Skill(
   @JsonProperty("topLevelId") String topLevelId,
   @JsonProperty("skillId") String skillId,
   @JsonProperty("levelSkill") Integer level
) {
}
