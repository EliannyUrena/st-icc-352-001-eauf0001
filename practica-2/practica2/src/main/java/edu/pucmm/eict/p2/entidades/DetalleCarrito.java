package edu.pucmm.eict.p2.entidades;

public class DetalleCarrito {

    private Producto producto;
    private int cantidad;

    public DetalleCarrito() {

    }

    public DetalleCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

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
}
