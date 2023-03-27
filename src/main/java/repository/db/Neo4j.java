package repository.db;
import com.google.inject.Inject;
import core.config.Config;
import core.config.IConfig;
import core.config.ISystemDatabase;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.List;

public class Neo4j implements ISystemDatabase {

    private final Driver _driver;
    private final IConfig _config;

    @Inject
    public Neo4j(IConfig config) {
        _config = config;
        _driver = GraphDatabase.driver(config.dbUri(), AuthTokens.basic(config.dbUserName(), config.dbPassword()));
    }

    @Override
    public List<Record> runQuery(String query) {
        return readTx(query);
    }

    public List<Record> readTx(String query) {
        Session session = _driver.session(SessionConfig.forDatabase(_config.dbDatabase()));
        return session.readTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

    public List<Record> writeTx(String query) {
        Session session = _driver.session(SessionConfig.forDatabase(_config.dbUserName()));
        return session.writeTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

}
