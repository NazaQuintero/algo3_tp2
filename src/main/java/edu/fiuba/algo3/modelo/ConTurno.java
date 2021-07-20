package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ConTurno implements Turno {
    /*TODO: Tomar estrategia de Ataque y luego Reagrupe por cada jugador. Luego de que todos ataquen y reagrupen,
    se pasa a ronda de Colocacion para todos los jugadores */

    private final Jugadores jugadores;
    private Jugador actual;
    private Jugador primerJugador;

    private Ronda ronda;
    private int cantidadDeTurnosJugados;
    private int cantidadDeRondasJugadas;
    private Iterator<Jugador> iteradorDeJugadores;

    public ConTurno(Jugadores jugadores) {
        this.jugadores = jugadores;
        this.iteradorDeJugadores = jugadores.iterator();
        this.ronda = new Ataque();
        this.actual = this.iteradorDeJugadores.next();
        this.cantidadDeTurnosJugados = 0;
        this.cantidadDeRondasJugadas = 0;
    }

    public int obtenerCantidadDeTurnosPorRonda() {
        return this.jugadores.obtenerCantidad();
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.actual;
    }


    public void seleccionarPrimerJugador(int valor) {
        this.jugadores.setPrimerJugador(valor);
        this.primerJugador = this.jugadores.obtenerJugador(valor);
        this.iteradorDeJugadores = jugadores.iterator();
        this.jugadores.obtenerJugador(valor).setTurno(this);
        this.actual = this.iteradorDeJugadores.next();
    }

    public void finalizarTurnoActual() {
//        this.actual = (this.actual < (this.jugadores.obtenerCantidad() - 1)) ? (this.actual + 1) : 0;
        this.actual = this.iteradorDeJugadores.next();
        this.cantidadDeTurnosJugados++;
        this.cambiarRonda();
//        if(this.actual == this.primerJugador) {
//            this.ronda = this.ronda.obtenerSiguiente();
//            this.cantidadDeRondasJugadas++;
//        }
    }

    public int obtenerCantidadDeTurnosJugados() {
        return this.cantidadDeTurnosJugados;
    }

    @Override
    public void cambiarRonda() {
        if(this.actual == this.primerJugador) {
            this.ronda = new Colocacion();
        } else {
            this.ronda = new Ataque();
        }
    }

    public Ronda obtenerRondaActual() {
        return this.ronda;
    }

    public int obtenerCantidadDeRondasJugadas() {
        return this.cantidadDeRondasJugadas;
    }

    public void finalizarAtaque() {
        this.ronda = this.ronda.obtenerSiguiente();
    }

    public void finalizarReagrupe() {
        this.cantidadDeRondasJugadas++;
        this.finalizarTurnoActual();
    }

    public void finalizarColocacion() {
        this.cantidadDeRondasJugadas++;
        this.finalizarTurnoActual();
    }
}
