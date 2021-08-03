package edu.fiuba.algo3.modelo.continentes;

import java.util.HashMap;

public class MultitonContinentes {
    private static HashMap<String, Continenta> _continentes = new HashMap<>();

    private MultitonContinentes() {}

    public static void reiniciar() {
        _continentes = new HashMap<>();
    }

    public static void cargarContinentes(Continenta[] continentes) {
        for(Continenta continente: continentes) _continentes.put(continente.getNombre(), continente);
    }
    
    public static Continenta obtenerInstanciaDe(String nombreDeContinente) throws ContinenteInvalidoException {
        if(_continentes.get(nombreDeContinente) == null) throw new ContinenteInvalidoException();
        return _continentes.get(nombreDeContinente);
    }

}
