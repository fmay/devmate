package api.v1.finder.repository;

import api.v1.finder.request.FinderQuery;
import api.v1.finder.response.FinderResponse;

public interface IFinderRepository {
    FinderResponse execute(FinderQuery query);
}
