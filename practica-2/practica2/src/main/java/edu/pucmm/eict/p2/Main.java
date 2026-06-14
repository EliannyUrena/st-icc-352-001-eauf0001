package edu.pucmm.eict.p2;

import io.javalin.Javalin;

public class Main {
    void main() {
        IO.println("Hola");

        Javalin app = Javalin.create(config -> {

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            config.routes.get("/", ctx -> {
                ctx.result("Hola");
            });
        });

        app.start(7000);

    }
}

