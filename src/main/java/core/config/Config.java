package core.config;

import io.github.cdimascio.dotenv.Dotenv;

// Singleton Configuration class
public class Config implements IConfig {
    private final String dbUri;
    private final String dbPort;
    private final String dbUserName;
    private final String dbPassword;
    private final String dbDatabase;

    public Config() {
        // Get environment vars
        Dotenv dotenv;
        dotenv = Dotenv.configure().load();

        // Database
        dbUri = dotenv.get("DB_URI", "");
        dbPort = dotenv.get("DB_PORT", "");
        dbUserName = dotenv.get("DB_USERNAME", "");
        dbPassword = dotenv.get("DB_PASSWORD", "");
        dbDatabase = dotenv.get("DB_DBNAME", "");
    }

    @Override
    public String dbUri() {
        return dbUri;
    }

    @Override
    public String dbPort() {
        return dbPort;
    }

    @Override
    public String dbUserName() {
        return dbUserName;
    }

    @Override
    public String dbPassword() {
        return dbPassword;
    }

    @Override
    public String dbDatabase() {
        return dbDatabase;
    }
}
