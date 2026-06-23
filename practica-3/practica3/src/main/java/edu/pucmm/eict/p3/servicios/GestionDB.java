package edu.pucmm.eict.p3.servicios;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GestionDB<T> {

    private static EntityManagerFactory emf;
    private Class<T> claseEntidad;

    public GestionDB(Class<T> claseEntidad) {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }

        this.claseEntidad = claseEntidad;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public T crear(T entidad) throws EntityExistsException {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return entidad;
    }

}
