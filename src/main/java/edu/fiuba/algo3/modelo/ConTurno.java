package edu.fiuba.algo3.modelo;

public class ConTurno implements Turno {

    private final Jugadores jugadores;
    private Jugador actual;

    private Ronda ronda;
    private int cantidadDeTurnosJugados;

    public ConTurno(Jugadores jugadores) {
        this.jugadores = jugadores;
        this.setRonda(new Ataque());
        this.cambiarJugadorActual();
        this.cantidadDeTurnosJugados = 0;
    }

    public int obtenerCantidadDeTurnosPorRonda() {
        return this.jugadores.obtenerCantidad();
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.actual;
    }

    public void seleccionarPrimerJugador(int valor) {
        this.jugadores.setPrimerJugador(valor);
        this.jugadores.obtenerJugador(valor).setTurno(this);
        this.cambiarJugadorActual();
    }

    private void cambiarJugadorActual() {
        this.actual = this.jugadores.obtenerSiguiente();
    }

    private boolean leTocaALPrimerJugador() {
        return this.actual == this.jugadores.getPrimerJugador();
    }

    private void finalizarTurnoActual() {
        this.actual.setTurno(new SinTurno());
        this.cambiarJugadorActual();
        this.actual.setTurno(this);
        this.cantidadDeTurnosJugados++;
    }

    public int obtenerCantidadDeTurnosJugados() {
        return this.cantidadDeTurnosJugados;
    }

    public Ronda obtenerRondaActual() {
        return this.ronda;
    }

    public int obtenerCantidadDeRondasJugadas() {
        return this.cantidadDeTurnosJugados/jugadores.obtenerCantidad();
    }

    public void setRonda(Ronda unaRonda) {
        this.ronda = unaRonda;
    }

    public void finalizarAtaque() {
        this.setRonda(new Reagrupe());
    }

    public void finalizarReagrupe() {
        this.finalizarTurnoActual();
        if(leTocaALPrimerJugador()) this.setRonda(new Colocacion());
        else this.setRonda(new Ataque());
    }

    public void finalizarColocacion() {
        if(leTocaALPrimerJugador()) this.setRonda(new Ataque());
        this.finalizarTurnoActual();
    }
}
