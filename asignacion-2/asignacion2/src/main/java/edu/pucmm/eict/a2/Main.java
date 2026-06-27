package edu.pucmm.eict.a2;

//import edu.pucmm.eict.controladores.*;

import edu.pucmm.eict.a2.controladores.CrudTradicionalControlador;
import edu.pucmm.eict.a2.servicios.BootStrapServices;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hola Mundo en Javalin 7 :-D");

        BootStrapServices.getInstancia().init();

        Javalin app = Javalin.create(config -> {

            // Archivos estáticos servidos desde /publico en el classpath
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.aliasCheck = null;
            });

            // Motor de plantillas por defecto: Thymeleaf (usado en /crud-simple y /thymeleaf)
            config.fileRenderer(new JavalinThymeleaf());

            config.routes.apiBuilder(() -> {
                /**
                 * CRUD con plantillas Thymeleaf (flujo petición-respuesta tradicional).
                 * http://localhost:7000/crud-simple/listar
                 */
                path("/crud-simple/", () -> {
                    get(ctx -> ctx.redirect("/crud-simple/listar"));
                    get("/listar", CrudTradicionalControlador::listar);
                    get("/crear", CrudTradicionalControlador::crearEstudianteForm);
                    post("/crear", CrudTradicionalControlador::procesarCreacionEstudiante);
                    get("/visualizar/{matricula}", CrudTradicionalControlador::visualizarEstudiante);
                    get("/editar/{matricula}", CrudTradicionalControlador::editarEstudianteForm);
                    post("/editar", CrudTradicionalControlador::procesarEditarEstudiante);
                    get("/eliminar/{matricula}", CrudTradicionalControlador::eliminarEstudiante);
                });
            });

            // Endpoint raíz
            config.routes.get("/", ctx -> ctx.result("Hola Mundo en Javalin 7 :-D"));

        });
        app.start(getHerokuAssignedPort());
    }

    /**
     * Devuelve el puerto asignado por Heroku o 7000 en desarrollo local.
     */
    static int getHerokuAssignedPort() {
        String port = new ProcessBuilder().environment().get("PORT");
        return port != null ? Integer.parseInt(port) : 7000;
    }
}
