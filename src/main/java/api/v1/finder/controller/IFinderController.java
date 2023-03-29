package api.v1.finder.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.io.IOException;

public interface IFinderController {
    void init(Javalin javalin);

    void runQuery(Context ctx) throws IOException;
}
