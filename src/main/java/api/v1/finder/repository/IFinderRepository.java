package api.v1.finder.repository;

import api.v1.finder.request.FinderRequest;
import api.v1.finder.response.FinderResponse;

public interface IFinderRepository {
    FinderResponse execute(FinderRequest query);
}
