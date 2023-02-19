package services.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import repository.db.health.Version;

import java.util.Map;

public class HealthService {
    public static String database() {
        try {
            Map version = Version.execute();
            return new ObjectMapper().writeValueAsString(version);
        }
        catch(Exception e) {
            return "";
        }
    }
}
