package edu.pucmm.eict.p3.controladores;

import edu.pucmm.eict.p3.entidades.Foto;
import edu.pucmm.eict.p3.entidades.Producto;
import edu.pucmm.eict.p3.servicios.FotoServices;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FotoControlador {
    public static void listarFotos(Context ctx) {
        List<Foto> fotos = FotoServices.getInstancia().findAll();

        Map<String, Object> modelo = new HashMap<>();
        //modelo.put("titulo", "Ejemplo de funcionalidad Thymeleaf");
        modelo.put("fotos", fotos);
        ctx.render("/templates/listar.html", modelo);
    }
    public static List<Foto> procesarFotos(Context ctx, Producto producto) {

        List<Foto> fotos = new ArrayList<>();

        ctx.uploadedFiles("foto").forEach(uploadedFile -> {
            try {
                byte[] bytes = uploadedFile.content().readAllBytes();
                String encondedString = Base64.getEncoder().encodeToString(bytes);

                Foto foto = new Foto(uploadedFile.filename(), uploadedFile.contentType(), encondedString);
                foto.setNombre(uploadedFile.filename());
                foto.setMimeType(uploadedFile.contentType());
                foto.setFotoBase64(encondedString);
                foto.setProducto(producto);
                producto.getFotos().add(foto);

                FotoServices.getInstancia().crear(foto);
                fotos.add(foto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //ctx.redirect("/fotos/listar");

        });
        return fotos;
    }
}
