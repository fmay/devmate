package repository.db.finder;

import domain.finder.FinderQuery;
import domain.finder.FinderResponse;

public interface IFinderRepository {
    FinderResponse execute(FinderQuery query);
}
