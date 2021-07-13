package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Jugador> jugadores;

    public Juego() {
        jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador unJugador) {
        jugadores.add(unJugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        throw new CantidadDeJugadoresInsuficienteException();
    }
}
