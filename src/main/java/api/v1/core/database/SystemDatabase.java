package api.v1.core.database;

import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

public class SystemDatabase<T> {
    public List<T> runQuery(String query) {
        return new ArrayList<T>();
    }
}
