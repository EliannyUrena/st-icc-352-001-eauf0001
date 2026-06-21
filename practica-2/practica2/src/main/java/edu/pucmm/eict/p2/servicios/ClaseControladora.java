package edu.pucmm.eict.p2.servicios;

import edu.pucmm.eict.p2.entidades.*;

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
        listaProductos.add(new Producto(generarIdProducto(), "pc", new BigDecimal("4000")));
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

    public List<CarroCompra> getListaCarritos() { return listaCarritos; }

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

    public Producto actualizarProducto(Producto producto) {
        Producto temp = buscarProducto(producto.getId());

        if (temp == null) {
            return null;
        }

        temp.setNombre(producto.getNombre());
        temp.setPrecio(producto.getPrecio());

        return temp;
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

    public Usuario autenticarUsuario(String usuario, String password) {

        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public BigDecimal obtenerSubTotal(DetalleCarrito detalleCarrito) {
        return detalleCarrito.getProducto().getPrecio().multiply(BigDecimal.valueOf(detalleCarrito.getCantidad()));
    }

    public BigDecimal calcularTotal(CarroCompra carroCompra) {
        BigDecimal total = BigDecimal.valueOf(0);

        for (DetalleCarrito detalle : carroCompra.getListaProductos()) {
            BigDecimal subtotal = obtenerSubTotal(detalle);

            total = total.add(subtotal);
        }

        return total;
    }

    public void agregarProductoCarrito(CarroCompra carroCompra, Producto producto, int cantidad) {

        for (DetalleCarrito detalle : carroCompra.getListaProductos())
        {
            if(detalle.getProducto().getId() == producto.getId())
            {
                detalle.setCantidad(detalle.getCantidad() + cantidad);
                return;
            }
        }
        carroCompra.getListaProductos().add(new DetalleCarrito(producto, cantidad));
    }

}
