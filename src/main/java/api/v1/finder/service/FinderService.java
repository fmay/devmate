package api.v1.finder.service;

import api.v1.core.util.BodyToObject;
import com.google.gson.Gson;
import com.google.inject.Inject;
import api.v1.finder.request.FinderRequest;
import api.v1.finder.response.FinderResponse;
import jakarta.validation.*;
import api.v1.finder.repository.IFinderRepository;
import api.v1.core.logging.ILogging;
import java.io.IOException;

public class FinderService implements IFinderService {

    private final ILogging _logger;
    private final IFinderRepository _repo;

    @Inject
    public FinderService(ILogging logger, IFinderRepository finderRepository) {
        _logger = logger;
        _repo = finderRepository;
    }

    static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();

    @Override
    public String runQuery(String body) throws IOException, ValidationException {
        _logger.info("Finder Service runQuery()");
        FinderRequest request = BodyToObject.convert(body, FinderRequest.class);
        FinderResponse response = _repo.execute(request);
        return new Gson().toJson(response);
    }
}
