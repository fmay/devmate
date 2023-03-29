package api.v1.core.database;
import com.google.inject.Inject;
import api.v1.core.config.IConfig;
import api.v1.core.database.ISystemDatabase;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.List;

public class Neo4j implements ISystemDatabase<Record> {

    private final Driver _driver;
    private final IConfig _config;

    @Inject
    public Neo4j(IConfig config) {
        _config = config;
        _driver = GraphDatabase.driver(config.dbUri(), AuthTokens.basic(config.dbUserName(), config.dbPassword()));
    }

    @Override
    public List<Record> readQuery(String query) {
        return readTx(query);
    }

    // Transactional read query
    public List<Record> readTx(String query) {
        Session session = _driver.session(SessionConfig.forDatabase(_config.dbDatabase()));
        return session.readTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

    // Transactional write query
    public List<Record> writeTx(String query) {
        Session session = _driver.session(SessionConfig.forDatabase(_config.dbUserName()));
        return session.writeTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

}
