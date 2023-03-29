package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.health.controller.HealthController;
import api.v1.health.controller.IHealthController;
import api.v1.health.repository.HealthRepository;
import api.v1.health.repository.IHealthRepository;
import api.v1.health.service.HealthService;
import api.v1.health.service.IHealthService;

public class HealthModule extends AbstractModule {

    protected void configure() {
        bind(IHealthController.class).to(HealthController.class);
        bind(IHealthService.class).to(HealthService.class);
        bind(IHealthRepository.class).to(HealthRepository.class);
    }

}
