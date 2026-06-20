package edu.pucmm.eict.p2.controladores;

import edu.pucmm.eict.p2.entidades.CarroCompra;
import edu.pucmm.eict.p2.servicios.ClaseControladora;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarroCompraControlador {

    private final static ClaseControladora claseControladora = ClaseControladora.getInstancia();

    public static void verCarrito(Context ctx) {

        String nombreCliente = ctx.sessionAttribute("nombreCliente");
        CarroCompra carroCompra = claseControladora.obtenerCarrito(nombreCliente);

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Carrito de compra");
        modelo.put("carroCompra", carroCompra.getListaProductos());
        modelo.put("total", claseControladora.calcularTotal(carroCompra));
        modelo.put("nombreCliente", nombreCliente);

        ctx.render("/templates/carroCompra.html", modelo);

    }

}
