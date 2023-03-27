package core.config;

import org.neo4j.driver.Record;

import java.util.List;

public interface ISystemDatabase {
    List<Record> runQuery(String query);
}
