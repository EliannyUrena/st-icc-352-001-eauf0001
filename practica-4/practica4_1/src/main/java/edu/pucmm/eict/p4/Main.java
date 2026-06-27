package edu.pucmm.eict.p4;

import io.javalin.Javalin;

public class Main {

    void main() {

        var app = Javalin.create(config -> {

            config.routes.get("/", ctx -> {

                ctx.result("Bienvenido a la aplicacion 1");

            });

        });
        app.start(7000);

    }
}
