package presentation.health;

import io.javalin.Javalin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.javalin.http.Context;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealthControllerTest {

    private Context ctx;
    private Javalin app;
    private HealthController healthController;

    @BeforeEach
    public void setup() {
        ctx = mock(Context.class);
        app = mock(Javalin.class);
        healthController = new HealthController(app);
    }

    @Test
    void ping() {
        healthController.ping(ctx);
        verify(ctx).result("pong");
    }

//    @Test
//    void neo4j() {
//        HealthController mockNeo4j = mock(HealthController.class);
//        when(mockNeo4j.neo4j(ctx)).
//        verify(ctx).result("pong");
//    }

}