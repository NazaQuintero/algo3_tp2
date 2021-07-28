package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;

public class Destruccion implements Objetivo {

    private Jugador jugadorAEliminar;

    public Destruccion(Jugador jugadorAEliminar) {
        this.jugadorAEliminar = jugadorAEliminar;
    }

    @Override
    public boolean estaCumplido() {
        return jugadorAEliminar.cantidadPaisesDominados() == 0;
    }
}
