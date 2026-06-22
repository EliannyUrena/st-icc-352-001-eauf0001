package edu.pucmm.eict.p3.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaProductos {

    private long id;
    private Date fechaCompra;
    private String nombreCliente;
    private List<DetalleCarrito> listaProductos;

    public VentaProductos() {

        this.listaProductos = new ArrayList<>();
        this.fechaCompra = new Date();
    }

    public VentaProductos(long id, String nombreCliente) {
        this.id = id;
        this.fechaCompra = new Date();
        this.nombreCliente = nombreCliente;
        this.listaProductos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<DetalleCarrito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DetalleCarrito> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public BigDecimal calcularTotalProductosVenta() {
        BigDecimal total = BigDecimal.valueOf(0);

        for (DetalleCarrito detalle : listaProductos) {
            BigDecimal subtotal = detalle.getProducto().getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad()));

            total = total.add(subtotal);

        }
        return total;
    }



}
