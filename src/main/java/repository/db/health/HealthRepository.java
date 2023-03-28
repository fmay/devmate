package repository.db.health;

import com.google.inject.Inject;
import core.config.ISystemDatabase;
import org.neo4j.driver.Record;

import java.util.List;
import java.util.Map;

public class HealthRepository implements IHealthRepository {

    private final ISystemDatabase _db;

    @Inject
    public HealthRepository(ISystemDatabase db) {
        _db = db;
    }

    public Map<String, Object> databaseInfo() {
        String query = "call dbms.components() yield name, versions, edition unwind versions as version return name, version, edition;";
        List<Record> result = _db.readQuery(query);
        return result.get(0).asMap();
    }

}
