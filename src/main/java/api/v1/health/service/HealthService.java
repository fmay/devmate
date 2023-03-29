package api.v1.health.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import api.v1.health.repository.IHealthRepository;
import api.v1.core.logging.ILogging;

import java.util.Map;

public class HealthService implements IHealthService {
    private final IHealthRepository _healthRepository;
    private ILogging _logger;

    @Inject
    public HealthService(IHealthRepository healthRepository, ILogging logger) {
        _healthRepository = healthRepository;
        _logger = logger;
    }

    @Override
    public String databaseInfo() {
        try {
            _logger.debug("Database Info Svc");
            Map<String, Object> version = _healthRepository.databaseInfo();
            return new ObjectMapper().writeValueAsString(version);
        }
        catch(Exception e) {
            return "";
        }
    }


}
