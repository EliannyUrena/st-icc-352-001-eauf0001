package edu.pucmm.eict.p3.servicios;

import edu.pucmm.eict.p3.entidades.Foto;

public class FotoServices extends GestionDB<Foto>{

    private static FotoServices instancia;

    private FotoServices() {
        super(Foto.class);
    }

    public static FotoServices getInstancia(){
        if(instancia==null){
            instancia = new FotoServices();
        }
        return instancia;
    }
}
