package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Ocupacion4 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar Europa, 4 países de Asia y 2 países de América de Sur.";
    }
}
