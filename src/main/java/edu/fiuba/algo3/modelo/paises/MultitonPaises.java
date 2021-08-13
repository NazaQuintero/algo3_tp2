package edu.fiuba.algo3.modelo.paises;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MultitonPaises {

    private static HashMap<String, Pais> _paises = new HashMap<>();

    private MultitonPaises() {}

    public static Pais obtenerInstanciaDe(String nombreDePais) {
        return _paises.get(nombreDePais);
    }

    public static Collection<Pais> obtenerTodosLosPaises() {
        return _paises.values();
    }

    public static void cargarPaises(ArrayList<Pais> paises) {
        for (Pais pais: paises) _paises.put(pais.getNombre(), pais);
    }
}
