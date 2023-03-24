package services.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.db.health.HealthRepository;

import java.util.Map;

public class HealthServiceImpl implements IHealthService {

    private HealthRepository _healthRepository;

    public HealthServiceImpl(HealthRepository healthRepository) {
        _healthRepository = healthRepository;
    }

    @Override
    public String databaseInfo() {
        try {
            Map<String, Object> version = _healthRepository.databaseInfo();
            return new ObjectMapper().writeValueAsString(version);
        }
        catch(Exception e) {
            return "";
        }
    }
}
