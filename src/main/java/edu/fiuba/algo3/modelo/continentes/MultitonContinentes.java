package edu.fiuba.algo3.modelo.continentes;

import java.util.HashMap;

public class MultitonContinentes {
    private static HashMap<String, Continente> continentes = new HashMap<>();

    private MultitonContinentes() { }

    public static void reiniciar() {
        continentes = new HashMap<>();
    }
    
    public static Continente obtenerInstanciaDe(String nombreDeContinente) {
        if(continentes.get(nombreDeContinente) == null) {
            Continente unContinente = crearInstanciaDeContinente(nombreDeContinente);
            nombreDeContinente = unContinente.obtenerNombre();
            continentes.put(nombreDeContinente, unContinente);
        }
        return continentes.get(nombreDeContinente);
    }

    private static Continente crearInstanciaDeContinente(String nombreContinente) {
        switch (nombreContinente){
            case "Africa": return new Africa();
            case "Oceania": return new Oceania();
            case "Europa": return new Europa();
            case "America del Norte": return new AmericaDelNorte();
            case "America del Sur": return new AmericaDelSur();
            default: return new Asia();
        }

    }
}
