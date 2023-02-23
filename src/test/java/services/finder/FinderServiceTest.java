package services.finder;

import domain.finder.FinderQuery;
import domain.finder.FinderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.db.finder.FinderRepository;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinderServiceTest {

    @Mock
    FinderRepository mockFinderRepository;

    @Test
    void runQuery() throws IOException {
        FinderQuery finderQuery = new FinderQuery("");
        FinderResponse expectedResponse = new FinderResponse();
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        FinderService finderService = new FinderService();
        when(finderService.runQuery(anyString())).thenReturn(String.valueOf(expectedResponse));
    }
}