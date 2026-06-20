package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CrudControladorProducto {

    private final static ClaseControladora claseControladora = ClaseControladora.getInstancia();

    public static void listarProductos(Context ctx) {

        List<Producto> productos = claseControladora.getListaProductos();
        Map<String, Object> modelo = new HashMap<>();

        modelo.put("titulo", "Listado de productos");
        modelo.put("listaProductos", productos);
        modelo.put("usuarioEsAdmin", usuarioEsAdmin(ctx));

        IO.println(ctx.sessionAttribute("usuario"));

        ctx.render("/templates/listarProductos.html", modelo);
    }

    public static void crearProductosForm(Context ctx) {

        if(!validarAdmin(ctx)) {
            return;
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Crear Producto");
        modelo.put("accion", "/crudProductos/crear");

        ctx.render("/templates/crearEditarProductos.html", modelo);
    }

    public static void procesarCrearProductos(Context ctx) {

        if(!validarAdmin(ctx)) {
            return;
        }

        String nombre = ctx.formParam("nombre");
        BigDecimal precio = new BigDecimal(Objects.requireNonNull(ctx.formParam("precio")));

        Producto producto = new Producto(nombre, precio);

        claseControladora.crearProducto(producto);

        IO.println(producto.getNombre());

        ctx.redirect("/crudProductos");
    }

    public static void editarProductosForm(Context ctx) {

        if(!validarAdmin(ctx)) {
            return;
        }

        int id = ctx.pathParamAsClass("id", Integer.class).required().get();

        Producto producto = claseControladora.buscarProducto(id);

        if (producto == null) {
            ctx.redirect("/crudProductos");
            return;
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Editar producto: " + producto.getId());
        modelo.put("producto", producto);
        modelo.put("accion", "/crudProductos/editar");

        ctx.render("/templates/crearEditarProductos.html", modelo);
    }

    public static void procesarEditarProducto(Context ctx) {

        if(!validarAdmin(ctx)) {
            return;
        }

        IO.println("EDITAR");
        int id = ctx.formParamAsClass("id", Integer.class).required().get();
        String nombre = ctx.formParam("nombre");
        BigDecimal precio = new BigDecimal(Objects.requireNonNull(ctx.formParam("precio")));

        Producto tmp = new Producto(id, nombre, precio);

        claseControladora.actualizarProducto(tmp);

        ctx.redirect("/crudProductos");
    }

    public static void eliminarProducto(Context ctx) {

        if(!validarAdmin(ctx)) {
            return;
        }

        int id = ctx.pathParamAsClass("id", Integer.class).required().get();

        claseControladora.eliminarProducto(id);

        ctx.redirect("/crudProductos");
    }

    private static boolean validarAdmin(Context ctx) {

        Usuario usuario = ctx.sessionAttribute("usuario");

        if (usuario == null || !usuario.getUsuario().equals("admin")) {
            ctx.redirect("/");
            return false;
        }

        return true;
    }

    private static boolean usuarioEsAdmin(Context ctx) {

        Usuario usuario = ctx.sessionAttribute("usuario");

        if (usuario != null && usuario.getUsuario().equals("admin")){
            return true;
        }
        return false;
    }


}
