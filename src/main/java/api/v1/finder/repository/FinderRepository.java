package api.v1.finder.repository;

import com.google.inject.Inject;
import api.v1.core.database.ISystemDatabase;
import api.v1.finder.request.FinderQuery;
import api.v1.finder.response.FinderResponse;

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
