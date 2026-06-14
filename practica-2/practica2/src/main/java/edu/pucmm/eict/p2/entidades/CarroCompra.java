package edu.pucmm.eict.p2.entidades;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {

    private long id;
    private List<DetalleCarrito> listaProductos;

    public CarroCompra() {
        this.listaProductos = new ArrayList<>();
    }
    public CarroCompra(long id, List<DetalleCarrito> listaProductos) {
        this.id = id;
        this.listaProductos = listaProductos;
    }

    public List<DetalleCarrito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DetalleCarrito> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
