package api.v1.core.modules;

import com.google.inject.AbstractModule;
import api.v1.finder.controller.FinderController;
import api.v1.finder.controller.IFinderController;
import api.v1.finder.repository.FinderRepository;
import api.v1.finder.repository.IFinderRepository;
import api.v1.finder.service.FinderService;
import api.v1.finder.service.IFinderService;

public class FinderModule extends AbstractModule {

    protected void configure() {
        bind(IFinderController.class).to(FinderController.class);
        bind(IFinderService.class).to(FinderService.class);
        bind(IFinderRepository.class).to(FinderRepository.class);
    }

}
