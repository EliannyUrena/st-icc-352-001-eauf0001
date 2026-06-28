package edu.pucmm.eict.p3.servicios;

import edu.pucmm.eict.p3.entidades.Producto;
import edu.pucmm.eict.p3.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;


public class UsuarioServices extends GestionDB<Usuario>{

    private static UsuarioServices instancia;

    private UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    public Usuario buscarUsuario(String usuario) {

        EntityManager em = getEntityManager();

        try {

            Usuario u = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class).setParameter("usuario", usuario).getSingleResult();

            System.out.println("ENCONTRADO: " + u.getUsuario());

            return u;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario autenticarUsuario(String usuario, String password) {
        Usuario user = buscarUsuario(usuario);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }


}
