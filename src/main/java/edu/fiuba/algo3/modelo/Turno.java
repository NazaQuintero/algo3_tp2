package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Turno {
    /*TODO: Tomar estrategia de Ataque y luego Reagrupe por cada jugador. Luego de que todos ataquen y reagrupen,
    se pasa a ronda de Colocacion para todos los jugadores */

    private final ArrayList<Jugador> jugadores;
    private int actual;
    private int primerJugador;
    private Ronda ronda;
    private int cantidadDeTurnosJugados;
    private int cantidadDeRondasJugadas;

    public Turno(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.ronda = new AtaqueYReagrupe();
        this.actual = 0;
        this.cantidadDeTurnosJugados = 0;
        this.cantidadDeRondasJugadas = 0;
    }

    public int obtenerCantidadDeTurnosPorRonda() {
        return this.jugadores.size();
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.jugadores.get(actual);
    }

    public void seleccionarPrimerJugador(int valor) {
        this.primerJugador = valor;
        this.actual = valor;
    }

    public void finalizarTurnoActual() {
        this.actual = (this.actual < (this.jugadores.size())) ? (this.actual + 1) : 0;
        this.cantidadDeTurnosJugados++;
        if(this.actual == this.primerJugador) {
            this.ronda = this.ronda.obtenerSiguiente();
            this.cantidadDeRondasJugadas++;
        }
    }

    public int obtenerCantidadDeTurnosJugados() {
        return this.cantidadDeTurnosJugados;
    }

    public Ronda obtenerRondaActual() {
        return this.ronda;
    }

    public int obtenerCantidadDeRondasJugadas() {
        return this.cantidadDeRondasJugadas;
    }
}
