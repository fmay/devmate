package org.example;
import domain.application.Config;
import presentation.user.user_controller;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        // Initialise and create Configuration
        new Config();

        // Initialise Javalin
        Javalin app = Javalin.create(/*config*/);
        app.start(7070);

        // Before
        app.before(ctx -> {
            System.out.println(ctx.path());
        });

        // Controllers
        app.get("/", ctx -> ctx.result("Hello World"));
        user_controller userController = new user_controller(app);
    }

}