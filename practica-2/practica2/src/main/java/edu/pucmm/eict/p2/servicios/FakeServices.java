package edu.pucmm.eict.p2.servicios;

import edu.pucmm.eict.p2.entidades.Producto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FakeServices {

    private static FakeServices instancia;
    private List<Producto> listaProductos = new ArrayList<>();

    private FakeServices() {

        listaProductos.add( new Producto(1, "PC", new BigDecimal("40000")));
    }

    public static FakeServices getInstancia() {
        if (instancia == null) {
            instancia = new FakeServices();
        }
        return instancia;
    }

    public List<Producto> getListaProductos() {
        return  listaProductos;
    }


}
