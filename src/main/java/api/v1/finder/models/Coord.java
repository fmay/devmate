package api.v1.finder.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record Coord (
    @Min(-90)
    @Max(90)
    double lat,

    @Min(-90)
    @Max(90)
    double lng
){}
