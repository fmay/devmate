package api.v1.health.repository;

import java.util.Map;

public interface IHealthRepository {
    Map<String, Object> databaseInfo();
}
