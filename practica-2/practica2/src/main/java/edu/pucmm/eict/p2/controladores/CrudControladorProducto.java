package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudControladorProducto {

    private final static ClaseControladora claseControladora = ClaseControladora.getInstancia();

    public static void listarProductos(Context ctx) {

        List<Producto> productos = claseControladora.getListaProductos();
        Map<String, Object> modelo = new HashMap<>();

        modelo.put("titulo", "Listado de productos");
        modelo.put("listaProductos", productos);

        ctx.render("/templates/crudProductos/listarProductos.html", modelo);


    }
}
