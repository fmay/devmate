package repository.db;
import core.config.Config;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.List;

public class Neo4j {

    private final Driver driver;

    public Neo4j(String uri, String userName, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(userName, password));
    }

    public List<Record> readTx(String query) {
        Config config = Config.getInstance();
        Session session = driver.session(SessionConfig.forDatabase(config.dbDatabase));
        return session.readTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

    public List<Record> writeTx(String query) {
        Config config = Config.getInstance();
        Session session = driver.session(SessionConfig.forDatabase(config.dbUserName));
        return session.writeTransaction(tx -> {
            Result result = tx.run(query);
            return result.list();
        });
    }

}
