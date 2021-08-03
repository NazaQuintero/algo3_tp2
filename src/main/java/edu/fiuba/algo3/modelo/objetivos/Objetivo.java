package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.ContinenteInvalidoException;

public interface Objetivo {
    boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException;
    String obtenerDescripcion();
}
