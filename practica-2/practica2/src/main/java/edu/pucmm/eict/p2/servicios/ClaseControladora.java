package edu.pucmm.eict.p2.servicios;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.entidades.VentaProductos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ClaseControladora {

    private static ClaseControladora instancia;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<VentaProductos> listaVentaProductos = new ArrayList<>();

    private int contadorProducto = 1;
    private int contadorUsuario = 1;
    private int contadorVenta = 1;

    private ClaseControladora() {

        listaProductos.add( new Producto(1, "PC", new BigDecimal("40000")));
        listaUsuarios.add(new Usuario("admin", "elianny", "admin"));

    }

    public static ClaseControladora getInstancia() {
        if (instancia == null) {
            instancia = new ClaseControladora();
        }
        return instancia;
    }

    public List<Producto> getListaProductos() {
        return  listaProductos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<VentaProductos> getListaVentaProductos() {
        return listaVentaProductos;
    }

    private int generarIdProducto() {
        return contadorProducto++;
    }

    private int generarIdUsuario() {
        return contadorUsuario++;
    }

    private int generarIdVenta() {
        return contadorVenta++;
    }

}
