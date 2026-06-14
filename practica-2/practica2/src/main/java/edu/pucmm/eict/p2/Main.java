package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.FakeServices;
import io.javalin.Javalin;

import java.math.BigDecimal;


public class Main {

    void main() {
        IO.println("Hola");

        FakeServices s = FakeServices.getInstancia();

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


        });

        app.start(7000);

    }
}

