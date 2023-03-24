package repository.db.health;

import core.config.Config;
import org.neo4j.driver.Record;

import java.util.List;
import java.util.Map;

public class HealthRepository {

    public Map<String, Object> databaseInfo() {
        final Config config = Config.getInstance();
        String query = "call dbms.components() yield name, versions, edition unwind versions as version return name, version, edition;";
        List<Record> result = config.db.readTx(query);
        return result.get(0).asMap();
    }

}
