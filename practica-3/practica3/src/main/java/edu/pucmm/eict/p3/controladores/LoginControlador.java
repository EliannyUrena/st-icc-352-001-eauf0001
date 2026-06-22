package edu.pucmm.eict.p3.controladores;

import edu.pucmm.eict.p3.entidades.Usuario;
import edu.pucmm.eict.p3.servicios.ClaseControladora;
import io.javalin.config.JavalinConfig;

public class LoginControlador {

    public static void aplicarRutas(JavalinConfig config) {

        config.routes.post("/procesar-login", ctx -> {

            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");

            Usuario user = ClaseControladora.getInstancia().autenticarUsuario(usuario, password);

            if (user != null ) {
                ctx.sessionAttribute("usuario", user);

                ctx.redirect("/");
            } else {
                ctx.result("Usuario o contrasena incorrectos");
            }
        });

        config.routes.get("/login", ctx -> {
           ctx.render("/publico/login.html");
        });

        config.routes.get("/logout", ctx -> {
           ctx.sessionAttribute("usuario", null);
           ctx.redirect("/");
        });


    }
}
