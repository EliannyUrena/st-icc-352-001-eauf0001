package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.Producto;
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

        ctx.render("/templates/listarProductos.html", modelo);
    }

    public static void crearProductosForm(Context ctx) {

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Crear Producto");
        modelo.put("accion", "/crudProductos/crear");

        ctx.render("/templates/crearEditarProductos.html", modelo);
    }

    public static void procesarCrearProductos(Context ctx) {

        String nombre = ctx.formParam("nombre");
        BigDecimal precio = new BigDecimal(Objects.requireNonNull(ctx.formParam("precio")));

        Producto producto = new Producto(nombre, precio);

        claseControladora.crearProducto(producto);

        IO.println(producto.getNombre());

        ctx.redirect("/crudProductos");
    }
}
