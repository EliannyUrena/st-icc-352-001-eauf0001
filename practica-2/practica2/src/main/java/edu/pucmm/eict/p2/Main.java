package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.Javalin;


public class Main {

    void main() {
        IO.println("Hola");

        ClaseControladora s = ClaseControladora.getInstancia();

        for (Usuario u : s.getListaUsuarios()) {
            IO.println("ID: " + u.getUsuario());
            IO.println("Nombre: " + u.getNombre());
            IO.println(" : " + u.getPassword());
        }

        Javalin app = Javalin.create(config -> {

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            config.routes.get("/", ctx -> {
                ctx.result("Hola");
            });

            config.routes.get("/productos", ctx-> {
                ctx.json(ClaseControladora.getInstancia().getListaProductos());
            });


        });

        app.start(7000);

    }
}

