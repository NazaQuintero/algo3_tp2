package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public interface Objetivo {
    boolean estaCumplido(Jugador jugador);
    String obtenerDescripcion();
}
