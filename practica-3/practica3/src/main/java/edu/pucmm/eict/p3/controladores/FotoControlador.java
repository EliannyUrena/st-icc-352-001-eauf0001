package edu.pucmm.eict.p3.controladores;

import edu.pucmm.eict.p3.entidades.Foto;
import edu.pucmm.eict.p3.entidades.Producto;
import edu.pucmm.eict.p3.servicios.FotoServices;
import io.javalin.http.Context;

import java.util.*;

public class FotoControlador {

    public static List<Foto> procesarFotos(Context ctx, Producto producto) {

        List<Foto> fotos = new ArrayList<>();

        ctx.uploadedFiles("fotos").forEach(uploadedFile -> {
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
        });
        return fotos;
    }
}
