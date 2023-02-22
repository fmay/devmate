package domain.finder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.user.Skill;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinderQueryDTO {
    public double meLat;

    public  double meLng;

    BoundingBox boundingBox;

    int skip;

    int limit;

    QueryType type;

    Skill[] skills;
    String merlin;

}

