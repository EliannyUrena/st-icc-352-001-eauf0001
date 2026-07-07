package edu.pucmm.eict.p3.servicios;

import edu.pucmm.eict.p3.entidades.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ProductoServices extends GestionDB<Producto>{

    private static ProductoServices instancia;

    private ProductoServices() {
        super(Producto.class);
    }

    public static ProductoServices getInstancia() {
        if (instancia == null) {
            instancia = new ProductoServices();
        }
        return instancia;
    }

    public List<Producto> findAll(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT p FROM Producto p");
        List<Producto> lista = query.getResultList();
        return lista;
    }

    public List<Producto> listarPorPagina(int pagina, int cantidad) {

        EntityManager em = getEntityManager();

        return em.createQuery("select p from Producto p", Producto.class)
                .setFirstResult((pagina - 1) * cantidad)
                .setMaxResults(cantidad)
                .getResultList();
    }

    public long cantidadProductos() {

        EntityManager em = getEntityManager();

        return em.createQuery("select count(p) from Producto p", Long.class).getSingleResult();
    }
}
