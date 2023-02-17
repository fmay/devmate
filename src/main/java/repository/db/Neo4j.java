package repository.db;


import org.neo4j.driver.*;

public class Neo4j {

    private final Driver driver;
    public Neo4j() {
        driver = GraphDatabase.driver("bolt://0.0.0.0:7687/crotchetydev", AuthTokens.basic( "neo4j", "Qplates99"));
    }

    public void getUsers() {
        try (Session session = driver.session()) {
            String greeting = session.readTransaction(tx -> {
                Result result = tx.run("MATCH (u:User) return u");
                return result.list().toString();
            });
        }
    }

}
