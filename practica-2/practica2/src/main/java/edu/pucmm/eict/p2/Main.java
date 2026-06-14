package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.servicios.FakeServices;
import io.javalin.Javalin;

import java.math.BigDecimal;


public class Main {

    void main() {
        IO.println("Hola");

        FakeServices s = FakeServices.getInstancia();

        for (Producto p : s.getListaProductos()) {
            IO.println("ID: " + p.getId());
            IO.println("Nombre: " + p.getNombre());
            IO.println("Precio: " + p.getPrecio());
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

