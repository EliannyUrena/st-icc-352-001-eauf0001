package edu.pucmm.eict.p2.servicios;

import edu.pucmm.eict.p2.entidades.CarroCompra;
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
    private List<CarroCompra> listaCarritos = new ArrayList<>();


    private int contadorProducto = 1;
    private int contadorUsuario = 1;
    private int contadorVenta = 1;

    private ClaseControladora() {

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

    public Producto crearProducto(Producto p) {
        p.setId(generarIdProducto());
        listaProductos.add(p);

        return p;
    }

    public Producto eliminarProducto(int id) {
        Producto p = buscarProducto(id);

        if (p != null) {
            listaProductos.remove(p);
        }

        return p;
    }

    public Producto buscarProducto(int id) {
        for (Producto p : listaProductos) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }



}
