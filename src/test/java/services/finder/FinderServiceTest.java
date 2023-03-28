package services.finder;

import com.google.gson.Gson;
import domain.finder.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import repository.db.finder.IFinderRepository;
import services.logging.ILogging;
import java.io.IOException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FinderServiceTest {

    private IFinderRepository mockFinderRepo;
    private ILogging mockLogging;
    private FinderService finderService;

    @BeforeEach
    void setUp() {
        mockLogging = mock(ILogging.class);
        mockFinderRepo = mock(IFinderRepository.class);
        finderService = new FinderService(mockLogging, mockFinderRepo);
    }

    @Test
    void getUser() throws IOException {
        FinderQuery query = new FinderQuery();
        Coord tl = new Coord(52, 0.123);
        Coord br = new Coord(57.1, -1.65);
        BoundingBox bbox = new BoundingBox(tl, br);
        query.boundingBox = bbox;
        query.meLat = 52;
        query.meLng = 0;
        query.skip = 0;
        query.limit = 0;
        query.type = FinderQueryType.MAP;
        FinderResponse expected = new FinderResponse();
        when(mockFinderRepo.execute(any())).thenReturn(expected);
        String resultString = finderService.runQuery(new Gson().toJson(query));
        FinderResponse resultFinderResponse = new Gson().fromJson(resultString, FinderResponse.class);
        assertThat(expected).usingRecursiveComparison()
            .isEqualTo(resultFinderResponse);
    }
}