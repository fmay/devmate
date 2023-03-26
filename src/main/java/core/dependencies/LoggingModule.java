package core.dependencies;

import com.google.inject.AbstractModule;
import presentation.health.HealthController;
import presentation.health.IHealthController;
import repository.db.health.HealthRepository;
import repository.db.health.IHealthRepository;
import services.health.HealthService;
import services.health.IHealthService;
import services.logging.ILogging;
import services.logging.Logging;

public class LoggingModule extends AbstractModule {

    protected void configure() {
        bind(ILogging.class).to(Logging.class);
    }

}
