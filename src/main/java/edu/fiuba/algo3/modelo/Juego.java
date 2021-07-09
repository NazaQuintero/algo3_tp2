package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Jugador> jugadores;

    public Juego() {
        jugadores = new ArrayList<Jugador>();
    }

    public void agregarJugador(String color) {

        Jugador nuevoJugador = new Jugador(color);

        jugadores.add(nuevoJugador);
    }

    public void comenzar() throws CantidadInsuficienteException {

        throw new CantidadInsuficienteException();
    }
}
