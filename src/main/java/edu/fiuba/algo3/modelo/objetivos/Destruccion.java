package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;

public class Destruccion implements Objetivo {

    private Jugador jugadorAEliminar;

    public Destruccion(Jugador jugadorAEliminar) {
        this.jugadorAEliminar = jugadorAEliminar;
    }

    @Override
    public boolean estaCumplido(Jugador jugador) throws ContinenteInvalidoException {
        return jugadorAEliminar.cantidadPaisesDominados() == 0;
    }

    @Override
    public String obtenerDescripcion() {
        return "Destruir a " + jugadorAEliminar.getNombre();
    }
}
