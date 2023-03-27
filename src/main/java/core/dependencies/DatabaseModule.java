package core.dependencies;

import com.google.inject.AbstractModule;
import core.config.ISystemDatabase;
import repository.db.Neo4j;
import services.logging.ILogging;
import services.logging.Logging;

public class DatabaseModule extends AbstractModule {

    protected void configure() {
        bind(ISystemDatabase.class).to(Neo4j.class);
    }

}
