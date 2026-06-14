package edu.pucmm.eict.p2.servicios;

import edu.pucmm.eict.p2.entidades.Producto;
import edu.pucmm.eict.p2.entidades.Usuario;
import edu.pucmm.eict.p2.entidades.VentasProductos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FakeServices {

    private static FakeServices instancia;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<VentasProductos> listaVentaProductos = new ArrayList<>();

    private FakeServices() {

        listaProductos.add( new Producto(1, "PC", new BigDecimal("40000")));
        listaUsuarios.add(new Usuario("admin", "elianny", "admin"));

    }

    public static FakeServices getInstancia() {
        if (instancia == null) {
            instancia = new FakeServices();
        }
        return instancia;
    }

    public List<Producto> getListaProductos() {
        return  listaProductos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<VentasProductos> getListaVentaProductos() {
        return listaVentaProductos;
    }

}
