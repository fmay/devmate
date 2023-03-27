package core.dependencies;

import com.google.inject.AbstractModule;
import presentation.finder.FinderController;
import presentation.finder.IFinderController;
import repository.db.finder.FinderRepository;
import repository.db.finder.IFinderRepository;
import services.finder.FinderService;
import services.finder.IFinderService;

public class FinderModule extends AbstractModule {

    protected void configure() {
        bind(IFinderController.class).to(FinderController.class);
        bind(IFinderService.class).to(FinderService.class);
        bind(IFinderRepository.class).to(FinderRepository.class);
    }

}
