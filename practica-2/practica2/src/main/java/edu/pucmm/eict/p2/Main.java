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
        IO.println("Hola");

        ClaseControladora s = ClaseControladora.getInstancia();

        Javalin app = Javalin.create(config -> {

            config.fileRenderer(new JavalinThymeleaf());

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            LoginControlador.aplicarRutas(config);

            // Motor de plantillas por defecto: Thymeleaf (usado en /crud-simple y /thymeleaf)



            // Rutas del API REST (/api/estudiante) y CRUD tradicional (/crud-simple)
            config.routes.apiBuilder(() -> {

                path("/crudProductos", () -> {
                    get(CrudControladorProducto::listarProductos);
                });


                /**
                 * CRUD con plantillas Thymeleaf (flujo petición-respuesta tradicional).
                 * http://localhost:7000/crud-simple/listar
                 */
                path("/crudProductos/", () -> {
                    get(ctx -> ctx.redirect("/crudProductos/listarProductos"));
                    get("/listarProductos", CrudControladorProducto::listarProductos);
                    // get("/crear", CrudTradicionalControlador::crearEstudianteForm);
                    // post("/crear", CrudTradicionalControlador::procesarCreacionEstudiante);
                    // get("/visualizar/{matricula}", CrudTradicionalControlador::visualizarEstudiante);
                    // get("/editar/{matricula}", CrudTradicionalControlador::editarEstudianteForm);
                    // post("/editar", CrudTradicionalControlador::procesarEditarEstudiante);
                    // get("/eliminar/{matricula}", CrudTradicionalControlador::eliminarEstudiante);
                });
            });


            config.routes.get("/", ctx -> {
                ctx.result("Hola");

            });



        });

        app.start(7000);

    }
}

