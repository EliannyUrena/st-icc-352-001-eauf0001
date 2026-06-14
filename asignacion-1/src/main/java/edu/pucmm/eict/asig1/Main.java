package edu.pucmm.eict.asig1;

import io.javalin.Javalin;

public class Main {

    record Usuario(String usuario, String password) {

    }

    void main() {

    var app = Javalin.create(config -> {

        config.staticFiles.add(staticFileConfig -> {
            staticFileConfig.directory="/publico";
            staticFileConfig.hostedPath="/";
        });

        config.routes.get("/", ctx -> {

            Usuario usuario = ctx.sessionAttribute("usuario");

            if (usuario != null)
            {
                ctx.result("Bienvenido/a " + usuario.usuario());
            }
        });

        config.routes.before("/", ctx-> {

            Usuario usuario = ctx.sessionAttribute("usuario");

            if (usuario == null) {
                ctx.redirect("/login.html");
                return;
            }

            if (!usuario.usuario().equals("Elianny") || !usuario.password().equals("1234")) {
                ctx.redirect("/login.html");
                return;
            }
        });

        config.routes.post("/procesar-login", ctx -> {

            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            ctx.sessionAttribute("usuario", new Usuario(usuario, password));

            //ctx.result("Usuario " +usuario + " password " + password);
            ctx.redirect("/");
        });
    });
    app.start(7000);
    }
}
