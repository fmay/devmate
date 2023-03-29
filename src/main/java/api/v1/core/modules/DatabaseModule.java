package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.core.database.ISystemDatabase;
import api.v1.core.database.Neo4j;

public class DatabaseModule extends AbstractModule {

    protected void configure() {
        bind(ISystemDatabase.class).to(Neo4j.class).asEagerSingleton();
    }

}
