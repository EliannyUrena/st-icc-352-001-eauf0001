package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.controladores.LoginControlador;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.Javalin;


public class Main {

    void main() {
        IO.println("Hola");

        ClaseControladora s = ClaseControladora.getInstancia();

        Javalin app = Javalin.create(config -> {

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            LoginControlador.aplicarRutas(config);


            config.routes.get("/", ctx -> {
                ctx.result("Hola");
                
            });
            /*
            config.routes.get("/productos", ctx-> {
                ctx.json(ClaseControladora.getInstancia().getListaProductos());
            });*/



        });

        app.start(7000);

    }
}

