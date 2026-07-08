package edu.pucmm.eict.P5.Servicios;

import edu.pucmm.eict.p3.entidades.VentaProductos;
import edu.pucmm.eict.p3.servicios.GestionDB;

public class VentaProductosServices extends GestionDB<VentaProductos> {
    private static VentaProductosServices instancia;

    public VentaProductosServices() {super(VentaProductos.class);}

    public static VentaProductosServices getInstancia(){
        if (instancia == null){
            instancia = new VentaProductosServices();
        }
        return instancia;
    }
}
