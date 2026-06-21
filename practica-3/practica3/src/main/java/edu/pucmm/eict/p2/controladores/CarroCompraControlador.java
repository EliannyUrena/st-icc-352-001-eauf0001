package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.CarroCompra;
import edu.pucmm.eict.p2.entidades.DetalleCarrito;
import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.entidades.VentaProductos;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.*;

public class CarroCompraControlador {

    private final static ClaseControladora claseControladora = ClaseControladora.getInstancia();

    public static void verCarrito(Context ctx) {

            CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

            if (carroCompra == null) {
                carroCompra = new CarroCompra();
            }

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("titulo", "Carrito de compra");
            modelo.put("carroCompra", carroCompra.getListaProductos());
            modelo.put("total", claseControladora.calcularTotal(carroCompra));

            ctx.render("/templates/carroCompra.html", modelo);

    }

    public static void procesarCompra(Context ctx) {

        CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");
        String nombreCliente = ctx.formParam("nombreCliente");

        if (carroCompra == null || carroCompra.getListaProductos().isEmpty()) {
            ctx.redirect("/carroCompra/ver");
            return;
        }

        VentaProductos venta = new VentaProductos();
        venta.setNombreCliente(nombreCliente);
        venta.setListaProductos(claseControladora.copiarProductosCarrito(carroCompra));

        claseControladora.getListaVentaProductos().add(venta);
        carroCompra.getListaProductos().clear();

        ctx.sessionAttribute("carroCompra", new CarroCompra());

        ctx.redirect("/carroCompra/ver");
    }


    public static void agregarProductoCarrito(Context ctx) {

        int idProducto = ctx.pathParamAsClass("id", Integer.class).required().get();
        int cantidad = ctx.formParamAsClass("cantidad", Integer.class).get();
        Producto producto = claseControladora.buscarProducto(idProducto);

        CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

        if (carroCompra == null) {
            carroCompra = new CarroCompra();
            carroCompra.setListaProductos(new ArrayList<>());
        }

        claseControladora.agregarProductoCarrito(carroCompra, producto, cantidad);

        ctx.sessionAttribute("carroCompra", carroCompra);
        ctx.redirect("/");

    }

    public static void eliminarProductoCarrito(Context ctx) {

        int id = ctx.pathParamAsClass("id", Integer.class).required().get();
        CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");
        claseControladora.eliminarElementoCarrito(carroCompra, id);

        ctx.sessionAttribute("carroCompra", carroCompra);
        ctx.redirect("/carroCompra/ver");
    }

    public static void limpiarCarrito(Context ctx) {

        CarroCompra carrito = ctx.sessionAttribute("carroCompra");

        if (carrito != null) {
            carrito.getListaProductos().clear();
        }

        ctx.sessionAttribute("carroCompra", carrito);

        ctx.redirect("/carroCompra/ver");
    }

    public static void listarVentas(Context ctx) {

        List<VentaProductos> venta = claseControladora.getListaVentaProductos();
        Map<String, Object> modelo = new HashMap<>();

        modelo.put("titulo", "Listado de ventas");
        modelo.put("listaVentas", venta);

        ctx.render("/templates/listarVentas.html", modelo);
    }

}
