package api.v1.health.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.get;

public interface IHealthController {

    void init(Javalin javalin);

    void ping(Context context);

    void database(Context context);
}
