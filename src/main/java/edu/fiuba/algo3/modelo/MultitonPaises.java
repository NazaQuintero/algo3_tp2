package edu.fiuba.algo3.modelo;

import java.util.Collection;
import java.util.HashMap;

public class MultitonPaises {

    private static HashMap<String, Pais> paises = new HashMap<>();

    private MultitonPaises() {}

    public static void reiniciar() {
        paises = new HashMap<>();
    }

    public static Pais obtenerInstanciaDe(String nombreDePais) {
        if(paises.get(nombreDePais) == null) {
            paises.put(nombreDePais, new Pais(nombreDePais));
        }
        return paises.get(nombreDePais);
    }

    public static Collection<Pais> obtenerTodosLosPaises() {
        return paises.values();
    }

}
