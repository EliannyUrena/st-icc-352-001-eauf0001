package edu.pucmm.eict.p3.servicios;

import edu.pucmm.eict.p3.entidades.Usuario;
import org.h2.tools.Server;
import java.sql.SQLException;
import java.util.List;

public class BootStrapServices {

    private static BootStrapServices instancia;

    private BootStrapServices(){

    }

    public static BootStrapServices getInstancia(){
        if(instancia == null){
            instancia=new BootStrapServices();
        }
        return instancia;
    }

    public void startDb() {
        try {
            //Modo servidor H2.
            Server.createTcpServer("-tcpPort",
                    "9092",
                    "-tcpAllowOthers",
                    "-tcpDaemon",
                    "-ifNotExists").start();
            //Abriendo el cliente web. El valor 0 representa puerto aleatorio.
            String status = Server.createWebServer("-trace", "-webPort", "0").start().getStatus();
            System.out.println("Status Web: "+status);
        }catch (SQLException ex){
            System.out.println("Problema con la base de datos: "+ex.getMessage());
        }
    }

    public void crearUsuarioAdmin() {

        UsuarioServices usuario = UsuarioServices.getInstancia();
        List<Usuario> listaUsuarios = usuario.findAll();

        if (listaUsuarios.isEmpty()) {

            Usuario admin = new Usuario();
            admin.setUsuario("admin");
            admin.setNombre("Administrador");
            admin.setPassword("admin");

            usuario.crear(admin);

        }



    }

    public void init(){
        startDb();
        crearUsuarioAdmin();
    }
}
