package domain.finder;

public record FinderResponse(
    FinderPoint[] mapPoints,
    FinderPoint[] listingPoints,
    int count
) {
    public FinderResponse() {
        this(new FinderPoint[] {}, new FinderPoint[] {}, 0);
    }
}
