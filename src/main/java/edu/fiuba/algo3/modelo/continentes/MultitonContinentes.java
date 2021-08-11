package edu.fiuba.algo3.modelo.continentes;

import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class MultitonContinentes {

    private static HashMap<String, Continente> continentes = new HashMap<>();

    private MultitonContinentes() {}

    public static void reiniciar() {
        continentes = new HashMap<>();
    }

    public static int cantidadDeContinentes() {
        return continentes.size();
    }

    public static void cargarContinentes(Continente[] continentes) {
        Arrays.stream(continentes).forEach(continente -> MultitonContinentes.continentes.
                put(continente.getNombre(), continente));
    }
    
    public static Continente obtenerInstanciaDe(String nombreContinente) throws ContinenteInvalidoException {
        if (continentes.get(nombreContinente) == null) throw new ContinenteInvalidoException();
        return continentes.get(nombreContinente);
    }

    public static Collection<Continente> obtenerContinentes() {
        return continentes.values();
    }

}
