package edu.pucmm.eict.p2.entidades;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {

    private long id;
    private List<Producto> listaProductos;

    public CarroCompra() {
        this.listaProductos = new ArrayList<>();
    }
    public CarroCompra(long id, List<Producto> listaProductos) {
        this.id = id;
        this.listaProductos = listaProductos;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
