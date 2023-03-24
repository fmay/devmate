package services.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import repository.db.health.HealthRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HealthServiceImplTest {

    @InjectMocks
    HealthServiceImpl healthServiceMock;

    private HealthRepository versionRepositoryMock;
    private HealthServiceImpl healthService;

    @BeforeEach
    void setUp() {
        versionRepositoryMock = mock(HealthRepository.class);
        healthService = new HealthServiceImpl(versionRepositoryMock);
    }

    @Test
    void database() {
        Map<String, Object> testMap = Map.of("version", "Version 1");
        when(versionRepositoryMock.databaseInfo()).thenReturn(testMap);
        String result = healthService.databaseInfo();
        assertEquals(result, "{\"version\":\"Version 1\"}");
    }
}