package api.v1.finder.response;

import api.v1.finder.models.FinderPoint;

public record FinderResponse(
    FinderPoint[] mapPoints,
    FinderPoint[] listingPoints,
    int count
) {
    public FinderResponse() {
        this(new FinderPoint[] {}, new FinderPoint[] {}, 0);
    }
}
