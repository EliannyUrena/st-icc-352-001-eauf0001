package edu.pucmm.eict.p3;

import edu.pucmm.eict.p3.controladores.CarroCompraControlador;
import edu.pucmm.eict.p3.controladores.CrudControladorProducto;
import edu.pucmm.eict.p3.controladores.Encriptador;
import edu.pucmm.eict.p3.controladores.LoginControlador;
import edu.pucmm.eict.p3.entidades.CarroCompra;
import edu.pucmm.eict.p3.entidades.Producto;
import edu.pucmm.eict.p3.entidades.Usuario;
import edu.pucmm.eict.p3.servicios.BootStrapServices;
import edu.pucmm.eict.p3.servicios.ClaseControladora;
import edu.pucmm.eict.p3.servicios.UsuarioServices;
import io.javalin.Javalin;


import io.javalin.rendering.template.JavalinThymeleaf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;


public class Main {

    void main() {

        BootStrapServices.getInstancia().init();

        UsuarioServices.getInstancia();
        EntityManager em = Persistence
                .createEntityManagerFactory("MiUnidadPersistencia")
                .createEntityManager();

        ClaseControladora claseControladora = ClaseControladora.getInstancia();

        Javalin app = Javalin.create(config -> {

            config.fileRenderer(new JavalinThymeleaf());

            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
            });

            config.routes.before(ctx -> {
                CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

                int cantidad = claseControladora.obtenerCantidadProductosCarrito(carroCompra);

                ctx.attribute("cantidad", cantidad);
            });

            config.routes.before(ctx -> {
                if (ctx.sessionAttribute("usuario") == null) {
                    String cookie = ctx.cookie("usuario");

                    if (cookie != null) {

                        try {
                            Encriptador encriptador = new Encriptador();
                            String nombreUsuario = encriptador.desencriptar(cookie);

                            System.out.println("NOMBREUSUARIO: " + nombreUsuario);
                            Usuario usuario = UsuarioServices.getInstancia().buscarUsuario(nombreUsuario);
                            System.out.println("USUARIO BD: " + usuario);


                            if (usuario != null) {
                                ctx.sessionAttribute("usuario", usuario);

                                IO.println("COOKIE: " + ctx.cookie("usuario"));
                            }

                        } catch (Exception e) {
                            ctx.removeCookie("usuario");
                        }
                    }
                }

            });

            LoginControlador.aplicarRutas(config);

            config.routes.before("/crudProductos/**", ctx -> {
                Usuario usuario = ctx.sessionAttribute("usuario");

                if (usuario == null || !usuario.getUsuario().equals("admin")){
                    ctx.redirect("/login");
                    return;
                }
            });

            config.routes.apiBuilder( () -> {
                get("/listarProductos", CrudControladorProducto::listarProductos);

            });


            config.routes.apiBuilder(() -> {

                path("/crudProductos", () -> {

                    get("/crear", CrudControladorProducto::crearProductosForm);
                    post("/crear", CrudControladorProducto::procesarCrearProductos);

                    get("/editar/{id}", CrudControladorProducto::editarProductosForm);
                    post("/editar", CrudControladorProducto::procesarEditarProducto);

                    get("/eliminar/{id}", CrudControladorProducto::eliminarProducto);

                    get("/listarVentas", CarroCompraControlador::listarVentas);
                });

                path("/carroCompra", () -> {
                    get("/ver", CarroCompraControlador:: verCarrito);
                    post("/procesar", CarroCompraControlador::procesarCompra);
                    post("/agregar/{id}", CarroCompraControlador::agregarProductoCarrito);
                    get("/eliminar/{id}", CarroCompraControlador::eliminarProductoCarrito);
                    get("/limpiar", CarroCompraControlador::limpiarCarrito);
                });

            });

            System.out.println(UsuarioServices.getInstancia().findAll());

            /*
            config.routes.get("/", ctx -> {
                ctx.result("Hola");

            });*/

            config.routes.get("/", CrudControladorProducto::listarProductos);

        });

        app.start(7000);

    }
}

