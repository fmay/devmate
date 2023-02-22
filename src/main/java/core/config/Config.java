package core.config;

import io.github.cdimascio.dotenv.Dotenv;
import repository.db.Neo4j;

// Singleton Configuration class
public class Config {

    private static Config instance = null;

    public final Neo4j db;
    public final String dbUri;
    public final String dbUserName;
    public final String dbPassword;
    public final String dbDatabase;

    public Config() {
        // Get environment vars
        Dotenv dotenv;
        dotenv = Dotenv.configure().load();
        dbUri = dotenv.get("DB_URI", "");
        dbUserName = dotenv.get("DB_USERNAME", "");
        dbPassword = dotenv.get("DB_PASSWORD", "");
        dbDatabase = dotenv.get("DB_DBNAME", "");

        // Connect to Neo4j
        db = new Neo4j(dbUri, dbUserName, dbPassword);
    }

    public static Config getInstance() {
        if (Config.instance == null) {
            Config.instance = new Config();
        }
        return Config.instance;
    }
}
