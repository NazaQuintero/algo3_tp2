package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Ocupacion3 implements Objetivo {
    @Override
    public boolean estaCumplido(Jugador jugador) {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar Asia y 2 países de América del Sur.";
    }
}
