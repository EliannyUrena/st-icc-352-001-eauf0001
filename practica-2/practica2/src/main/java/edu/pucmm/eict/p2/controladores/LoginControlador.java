package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
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
                ctx.result("Usuario o contraseña incorrectos");
            }
        });

        config.routes.get("/login", ctx -> {
           ctx.render("/publico/login.html");
        });


    }
}
