package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class General implements Objetivo {

    @Override
    public boolean estaCumplido(Jugador jugador) {
        return jugador.cantidadPaisesDominados() >= 30;
    }

    @Override
    public String obtenerDescripcion() {
        return "Ocupar 30 países";
    }
}
