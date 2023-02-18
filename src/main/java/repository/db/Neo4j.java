package repository.db;
import domain.application.Config;
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
//            while (result.hasNext()) {
//                System.out.println("Rec:" + result.next().get(0).toString());
//            }
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
