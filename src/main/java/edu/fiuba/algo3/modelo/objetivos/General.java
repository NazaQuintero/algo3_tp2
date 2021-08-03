package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.continentes.ContinenteInvalidoException;

public class General implements Objetivo {

    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return jugador.cantidadPaisesDominados() >= 30;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar 30 países";
    }
}
