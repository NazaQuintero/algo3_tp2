package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Ocupacion2 implements Objetivo {

    @Override
    public boolean estaCumplido(Jugador jugador) {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar América del Sur, 7 países de Europa y 3 países limítrofes entre sí en cualquier lugar del mapa.";
    }
}
