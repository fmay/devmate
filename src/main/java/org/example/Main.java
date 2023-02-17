package org.example;
import presentation.user_controller;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/);
        app.start(7070);

        app.get("/", ctx -> ctx.result("Hello World"));
        user_controller userController = new user_controller(app);
    }

}