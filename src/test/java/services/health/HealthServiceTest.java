package services.health;

import api.v1.health.service.HealthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.v1.health.repository.HealthRepository;
import api.v1.core.logging.ILogging;

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