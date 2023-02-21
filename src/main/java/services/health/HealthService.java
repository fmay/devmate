package services.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.db.health.VersionRepository;

import java.util.Map;

public class HealthService {
    public static String database() {
        try {
            Map<String, Object> version = VersionRepository.execute();
            return new ObjectMapper().writeValueAsString(version);
        }
        catch(Exception e) {
            return "";
        }
    }
}
