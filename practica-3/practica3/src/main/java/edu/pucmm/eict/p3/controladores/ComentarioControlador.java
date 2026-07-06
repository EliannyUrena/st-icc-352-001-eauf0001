package edu.pucmm.eict.p3.controladores;

import edu.pucmm.eict.p3.entidades.Comentario;
import edu.pucmm.eict.p3.entidades.Producto;
import edu.pucmm.eict.p3.entidades.Usuario;
import edu.pucmm.eict.p3.servicios.ComentarioServices;
import edu.pucmm.eict.p3.servicios.ProductoServices;
import io.javalin.http.Context;

public class ComentarioControlador {

    public static void procesarComentario(Context ctx) {

        Usuario usuario = ctx.sessionAttribute("usuario");

        if (usuario == null) {
            ctx.redirect("/login");
            return;
        }
        int productoId = ctx.pathParamAsClass("id", Integer.class).get();
        String texto = ctx.formParam("texto");

        Producto producto = ProductoServices.getInstancia().find(productoId);
        Comentario comentario = new Comentario();
        comentario.setProducto(producto);
        comentario.setTexto(texto);
        comentario.setUsuario(usuario.getUsuario());
        comentario.setHabilitado(true);

        producto.getComentarios().add(comentario);
        ComentarioServices.getInstancia().crear(comentario);

        ctx.redirect("/visualizarProductos/" + productoId);
    }

    public static void eliminarComentario(Context ctx) {
        Usuario usuario = ctx.sessionAttribute("usuario");

        if (usuario == null || !usuario.getUsuario().equals("admin")) {
            ctx.redirect("/login");
            return;
        }

        int idComentario = ctx.pathParamAsClass("id", Integer.class).get();
        Comentario comentario = ComentarioServices.getInstancia().find(idComentario);
        comentario.setHabilitado(false);
        ComentarioServices.getInstancia().editar(comentario);

        ctx.redirect("/visualizarProductos/" + comentario.getProducto().getId());


    }
}
