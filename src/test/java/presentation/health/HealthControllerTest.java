package presentation.health;

import io.javalin.Javalin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.javalin.http.Context;
import static org.mockito.Mockito.*;

class HealthControllerTest {

    private Context ctx;
    private HealthController healthController;

    @BeforeEach
    public void setup() {
        ctx = mock(Context.class);
        Javalin app = mock(Javalin.class);
        healthController = new HealthController(app);
    }

    @Test
    void ping() {
        healthController.ping(ctx);
        verify(ctx).result("pong");
    }


}