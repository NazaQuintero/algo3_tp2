package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.Random;

public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;

    public Juego() {
        jugadores = new ArrayList<>();
        tablero = new Tablero();
    }

    public void agregarJugador(Jugador unJugador) {
        jugadores.add(unJugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        throw new CantidadDeJugadoresInsuficienteException();
    }

    public void agregarPais(String nombrePais) { tablero.agregarPais(new Pais(nombrePais)); }

    public void repartirPaises() {

        tablero.repartirPaises(jugadores);

    }
}