package services.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import repository.db.health.HealthRepository;
import services.logging.ILogging;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HealthServiceTest {

    private HealthRepository versionRepositoryMock;
    private ILogging loggingMock;
    private HealthService healthService;

    @BeforeEach
    void setUp() {
        versionRepositoryMock = mock(HealthRepository.class);
        loggingMock = mock(ILogging.class);
        healthService = new HealthService(versionRepositoryMock, loggingMock);
    }

    @Test
    void database() {
        Map<String, Object> testMap = Map.of("version", "Version 1");
        when(versionRepositoryMock.databaseInfo()).thenReturn(testMap);
        String result = healthService.databaseInfo();
        assertEquals(result, "{\"version\":\"Version 1\"}");
    }
}