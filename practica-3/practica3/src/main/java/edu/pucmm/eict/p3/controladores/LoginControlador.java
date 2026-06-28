package edu.pucmm.eict.p3.controladores;

import edu.pucmm.eict.p3.servicios.ProductoServices;
import edu.pucmm.eict.p3.servicios.UsuarioServices;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import edu.pucmm.eict.p3.controladores.Encriptador;
import edu.pucmm.eict.p3.entidades.Usuario;
import edu.pucmm.eict.p3.servicios.ClaseControladora;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

public class LoginControlador {

    public static void aplicarRutas(JavalinConfig config) {

        config.routes.post("/procesar-login", ctx -> {

            String usuario = ctx.formParam("usuario");
            String password = ctx.formParam("password");
            String recordar = ctx.formParam("recordarUsuario");

            Usuario user = UsuarioServices.getInstancia().autenticarUsuario(usuario, password);

            if (user != null ) {
                ctx.sessionAttribute("usuario", user);

                if (recordar != null) {
                    crearCookie(ctx, user);
                }

                ctx.redirect("/");
            } else {
                ctx.result("Usuario o contrasena incorrectos");
            }
        });

        config.routes.get("/login", ctx -> {
           ctx.render("/publico/login.html");
        });

        config.routes.get("/logout", ctx -> {
           //ctx.sessionAttribute("usuario", null);
            ctx.req().getSession().invalidate();

            ctx.removeCookie("usuario");
            ctx.redirect("/");
        });


    }

    public static void crearCookie(Context ctx, Usuario usuario) {
        String user = usuario.getUsuario();
        Encriptador encriptador = new Encriptador();

        String encriptarUsuario = encriptador.encriptar(user);
        ctx.cookie("usuario", encriptarUsuario, 604800);
    }
}
