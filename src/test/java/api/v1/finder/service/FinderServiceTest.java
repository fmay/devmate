package services.finder;

import api.v1.finder.models.BoundingBox;
import api.v1.finder.models.Coord;
import api.v1.finder.models.FinderQueryType;
import api.v1.finder.request.FinderRequest;
import api.v1.finder.response.FinderResponse;
import api.v1.finder.service.FinderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.javalin.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import api.v1.finder.repository.IFinderRepository;
import api.v1.core.logging.ILogging;
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
        String request = "{\n" +
                "  \"meLat\": 52,\n" +
                "  \"meLng\": 0,\n" +
                "  \"skip\": 0,\n" +
                "  \"limit\": 10,\n" +
                "  \"type\": \"MAP\",\n" +
                "  \"boundingBox\": {\n" +
                "      \"topLeft\": {\n" +
                "          \"lat\": 52,\n" +
                "          \"lng\": 0.123\n" +
                "      },\n" +
                "      \"bottomRight\": {\n" +
                "          \"lat\": 57.1,\n" +
                "          \"lng\": -1.65\n" +
                "      }\n" +
                "  }\n" +
                "}";
        FinderResponse expected = new FinderResponse();
        when(mockFinderRepo.execute(any())).thenReturn(expected);
        String resultString = finderService.runQuery(request);
        FinderResponse resultFinderResponse = new Gson().fromJson(resultString, FinderResponse.class);
        assertThat(expected).usingRecursiveComparison()
                .isEqualTo(resultFinderResponse);
    }
}