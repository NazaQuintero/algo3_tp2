package edu.fiuba.algo3.modelo.continentes;

import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;

import java.util.HashMap;

public class MultitonContinentes {
    private static HashMap<String, Continente> _continentes = new HashMap<>();

    private MultitonContinentes() {}

    public static void reiniciar() {
        _continentes = new HashMap<>();
    }

    public static void cargarContinentes(Continente[] continentes) {
        for(Continente continente: continentes) _continentes.put(continente.getNombre(), continente);
    }
    
    public static Continente obtenerInstanciaDe(String nombreDeContinente) throws ContinenteInvalidoException {
        if(_continentes.get(nombreDeContinente) == null) throw new ContinenteInvalidoException();
        return _continentes.get(nombreDeContinente);
    }

}
