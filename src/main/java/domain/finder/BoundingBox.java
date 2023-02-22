package domain.finder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record BoundingBox(
    @Valid
    @NotNull
    Coord topLeft,

    @Valid
    @NotNull
    Coord bottomRight)
{}
