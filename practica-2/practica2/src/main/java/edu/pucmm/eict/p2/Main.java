package edu.pucmm.eict.p2;

import edu.pucmm.eict.p2.controladores.CarroCompraControlador;
import edu.pucmm.eict.p2.controladores.CrudControladorProducto;
import edu.pucmm.eict.p2.controladores.LoginControlador;
import edu.pucmm.eict.p2.entidades.CarroCompra;
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

        ClaseControladora claseControladora = ClaseControladora.getInstancia();

        Javalin app = Javalin.create(config -> {

            config.fileRenderer(new JavalinThymeleaf());

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            LoginControlador.aplicarRutas(config);

            config.routes.before(ctx -> {
                CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

                int cantidad = claseControladora.obtenerCantidadProductosCarrito(carroCompra);

                ctx.attribute("cantidad", cantidad);
            });


            config.routes.apiBuilder(() -> {

                path("/crudProductos", () -> {
                    get(CrudControladorProducto::listarProductos);

                    get("/crear", CrudControladorProducto::crearProductosForm);
                    post("/crear", CrudControladorProducto::procesarCrearProductos);

                    get("/editar/{id}", CrudControladorProducto::editarProductosForm);
                    post("/editar", CrudControladorProducto::procesarEditarProducto);

                    get("/eliminar/{id}", CrudControladorProducto::eliminarProducto);
                });

                path("/carroCompra", () -> {
                    get("/ver", CarroCompraControlador:: verCarrito);
                    post("/procesar", CarroCompraControlador::procesarCompra);
                    post("/agregar/{id}", CarroCompraControlador::agregarProductoCarrito);
                    get("/eliminar/{id}", CarroCompraControlador::eliminarProductoCarrito);
                    get("/limpiar", CarroCompraControlador::limpiarCarrito);
                });

            });

            /*
            config.routes.get("/", ctx -> {
                ctx.result("Hola");

            });*/

            config.routes.get("/", CrudControladorProducto::listarProductos);

        });

        app.start(7000);

    }
}

