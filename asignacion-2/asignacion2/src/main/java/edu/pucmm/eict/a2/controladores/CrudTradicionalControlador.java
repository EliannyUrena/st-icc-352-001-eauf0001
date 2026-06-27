package edu.pucmm.eict.a2.controladores;

import edu.pucmm.eict.a2.encapsulaciones.Estudiante;
import edu.pucmm.eict.a2.servicios.EstudianteServices;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa las rutas para manejar las operaciones de petición - respuesta.
 */
public class CrudTradicionalControlador  {

    private final static FakeServices fakeServices = FakeServices.getInstancia();


    public static void listar(@NotNull Context ctx) throws Exception {
        //tomando el parametro utl y validando el tipo.
        //List<Estudiante> lista = fakeServices.listarEstudiante();
        List<Estudiante> lista = EstudianteServices.getInstancia().findAll();

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Listado de Estudiante");
        modelo.put("lista", lista);
        //enviando al sistema de plantilla.
        ctx.render("/templates/crud-tradicional/listar.html", modelo);
    }

    public static void crearEstudianteForm(@NotNull Context ctx) throws Exception {
        //
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Formulario Creación Estudiante");
        modelo.put("accion", "/crud-simple/crear");
        //enviando al sistema de plantilla.
        ctx.render("/templates/crud-tradicional/crearEditarVisualizar.html", modelo);
    }

    public static void procesarCreacionEstudiante(@NotNull Context ctx) throws Exception {
        //obteniendo la información enviada.
        int matricula = ctx.formParamAsClass("matricula", Integer.class).required().get();
        String nombre = ctx.formParam("nombre");
        String carrera = ctx.formParam("carrera");
        //
        Estudiante tmp = new Estudiante(matricula, nombre, carrera);
        //realizar algún tipo de validación...
        //fakeServices.crearEstudiante(tmp); //puedo validar, existe un error enviar a otro vista.

        EstudianteServices.getInstancia().insertar(tmp);
        ctx.redirect("/crud-simple/");
    }

    public static void visualizarEstudiante(@NotNull Context ctx) throws Exception {
        //Estudiante estudiante = fakeServices.getEstudiantePorMatricula(ctx.pathParamAsClass("matricula", Integer.class).required().get());
        int matricula = ctx.pathParamAsClass("matricula", Integer.class).required().get();
        Estudiante estudiante = EstudianteServices.getInstancia().buscarEstPorMatricula(matricula);

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Formulario Visaulizar Estudiante "+estudiante.getMatricula());
        modelo.put("estudiante", estudiante);
        modelo.put("visualizar", true); //para controlar en el formulario si es visualizar
        modelo.put("accion", "/crud-simple/");

        //enviando al sistema de ,plantilla.
        ctx.render("/templates/crud-tradicional/crearEditarVisualizar.html", modelo);
    }

    public static void editarEstudianteForm(@NotNull Context ctx) throws Exception {


        //Estudiante estudiante = fakeServices.getEstudiantePorMatricula(ctx.pathParamAsClass("matricula", Integer.class).required().get());

        int matricula = ctx.pathParamAsClass("matricula", Integer.class).required().get();
        Estudiante estudiante = EstudianteServices.getInstancia().buscarEstPorMatricula(matricula);

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Formulario Editar Estudiante "+estudiante.getMatricula());
        modelo.put("estudiante", estudiante);
        modelo.put("accion", "/crud-simple/editar");

        //enviando al sistema de ,plantilla.
        ctx.render("/templates/crud-tradicional/crearEditarVisualizar.html", modelo);
    }

    public static void procesarEditarEstudiante(@NotNull Context ctx) throws Exception {

        int matricula = ctx.formParamAsClass("matricula", Integer.class).required().get();
        String nombre = ctx.formParam("nombre");
        String carrera = ctx.formParam("carrera");

        Estudiante tmp = new Estudiante(matricula, nombre, carrera);

        EstudianteServices.getInstancia().editar(tmp);

        ctx.redirect("/crud-simple/");
    }

    public static void eliminarEstudiante(@NotNull Context ctx) throws Exception {
        //fakeServices.eliminandoEstudiante(ctx.pathParamAsClass("matricula", Integer.class).required().get());
        int matricula = ctx.pathParamAsClass("matricula", Integer.class).required().get();
        EstudianteServices.getInstancia().eliminar(matricula);
        ctx.redirect("/crud-simple/");
    }

}
