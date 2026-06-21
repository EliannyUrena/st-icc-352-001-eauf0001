package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.CarroCompra;
import edu.pucmm.eict.p2.entidades.DetalleCarrito;
import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

        IO.println("Procesar compra");

        String nombreCliente = ctx.formParam("nombreCliente");

        if (nombreCliente == null) {
            ctx.redirect("/carroCompra/ver");
            return;
        }

        CarroCompra carroCompra = ctx.sessionAttribute("carroCompra");

        if (carroCompra == null || carroCompra.getListaProductos().isEmpty()) {
            ctx.redirect("/carroCompra/ver");
            return;
        }

        carroCompra.setNombreCliente(nombreCliente);

        claseControladora.getListaCarritos().add(carroCompra);

        ctx.sessionAttribute("carroCompra", new CarroCompra());

        ctx.redirect("/");
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

        System.out.println("id: " + idProducto);
        System.out.println("cant: " + cantidad);
        System.out.println("Cantidad en carrito: " + carroCompra.getListaProductos().size());

        ctx.sessionAttribute("carroCompra", carroCompra);
        ctx.redirect("/carroCompra/ver");

    }

}
