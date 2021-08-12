package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;

public interface Objetivo {

    boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException;

    String obtenerDescripcion();

}
