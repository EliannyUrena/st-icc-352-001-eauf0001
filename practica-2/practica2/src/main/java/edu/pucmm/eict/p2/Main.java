package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.controladores.CrudControladorProducto;
import edu.pucmm.eict.p2.controladores.LoginControlador;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.Javalin;


import io.javalin.rendering.template.JavalinThymeleaf;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;


public class Main {

    void main() {

        ClaseControladora s = ClaseControladora.getInstancia();

        Javalin app = Javalin.create(config -> {

            config.fileRenderer(new JavalinThymeleaf());

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            LoginControlador.aplicarRutas(config);


            config.routes.apiBuilder(() -> {

                path("/crudProductos", () -> {
                    get(CrudControladorProducto::listarProductos);

                    get("/crear", CrudControladorProducto::crearProductosForm);
                    post("/crear", CrudControladorProducto::procesarCrearProductos);

                    get("/editar/{id}", CrudControladorProducto::editarProductosForm);
                    post("/editar", CrudControladorProducto::procesarEditarProducto);
                });

            });


            config.routes.get("/", ctx -> {
                ctx.result("Hola");

            });

        });

        app.start(7000);

    }
}

