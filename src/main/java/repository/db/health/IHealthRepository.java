package repository.db.health;

import java.util.Map;

public interface IHealthRepository {
    Map<String, Object> databaseInfo();
}
