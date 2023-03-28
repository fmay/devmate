package domain.finder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.user.Skill;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinderQuery {
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

