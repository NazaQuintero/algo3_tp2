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
        this.actual.setTurno(new SinTurno());
        this.actual = this.iteradorDeJugadores.next();
        this.cambiarRonda();
        this.actual.setTurno(this);
        this.cantidadDeTurnosJugados++;
    }

    public int obtenerCantidadDeTurnosJugados() {
        return this.cantidadDeTurnosJugados;
    }

    @Override
    public void cambiarRonda() { // 1) pasar por el 1er jugador 2) rondaAct = reagrupe
        if(this.actual == this.primerJugador && this.cantidadDeTurnosJugados != 0) {
            this.ronda = this.ronda.obtenerSiguiente();
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
