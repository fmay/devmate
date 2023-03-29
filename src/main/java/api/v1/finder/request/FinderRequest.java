package api.v1.finder.request;

import api.v1.core.util.ValidationMessages;
import api.v1.finder.models.BoundingBox;
import api.v1.finder.models.FinderQueryType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import api.v1.user.models.Skill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinderRequest {
    @Min(-90)
    @Max(90)
    public double meLat;

    @Min(-90)
    @Max(90)
    public double meLng;

    public BoundingBox boundingBox;

    @Min(0)
    public int skip;

    @Range(min=0, max=100)
    public int limit;

    public FinderQueryType type;

    public Skill[] skills;
    public String merlin;

}

