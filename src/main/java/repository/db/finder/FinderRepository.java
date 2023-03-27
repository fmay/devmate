package repository.db.finder;

import com.google.inject.Inject;
import core.config.ISystemDatabase;
import domain.finder.FinderQuery;
import domain.finder.FinderResponse;

public class FinderRepository implements IFinderRepository {

    private ISystemDatabase _db;

    @Inject
    public FinderRepository(ISystemDatabase db) {
        _db = db;
    }

    public FinderResponse execute(FinderQuery query) {
        // Does nothing right now other than return an empty object
        return new FinderResponse();
    }
}
