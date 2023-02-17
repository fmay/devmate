package org.example;
import Presentation.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/users", UserController::getUsers);
        app.get("/user/{id}", UserController::getUser);
        app.start(7070);
    }
}