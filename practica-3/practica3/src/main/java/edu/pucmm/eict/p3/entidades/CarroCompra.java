package edu.pucmm.eict.p3.entidades;

import java.util.ArrayList;
import java.util.List;

public class CarroCompra {

    private long id;
    private String nombreCliente;
    private List<DetalleCarrito> listaProductos;

    public CarroCompra() {
        this.listaProductos = new ArrayList<>();
    }
    public CarroCompra(long id, String nombreCliente, List<DetalleCarrito> listaProductos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
