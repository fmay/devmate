package core.dependencies;

import com.google.inject.AbstractModule;
import presentation.health.HealthController;
import presentation.health.IHealthController;
import repository.db.health.HealthRepository;
import repository.db.health.IHealthRepository;
import services.health.HealthService;
import services.health.IHealthService;

public class HealthModule extends AbstractModule {

    protected void configure() {
        bind(IHealthController.class).to(HealthController.class);
        bind(IHealthService.class).to(HealthService.class);
        bind(IHealthRepository.class).to(HealthRepository.class);
    }

}
