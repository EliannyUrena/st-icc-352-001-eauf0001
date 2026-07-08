package edu.pucmm.eict.p3.entidades;

import jakarta.persistence.*;

@Entity
public class DetalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    private Producto producto;
    private int cantidad;
    @ManyToOne
    private VentaProductos venta;

    public DetalleCarrito() {

    }

    public DetalleCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public long getId() { return id;}

    public void setId(long id) { this.id = id;}

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public VentaProductos getVenta() { return venta; }

    public void setVenta(VentaProductos venta) { this.venta = venta; }

}
