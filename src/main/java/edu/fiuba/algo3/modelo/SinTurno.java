package edu.fiuba.algo3.modelo;

public class SinTurno implements Turno {
    @Override
    public int obtenerCantidadDeTurnosPorRonda() {
        return 0;
    }

    @Override
    public Jugador obtenerJugadorTurnoActual() {
        return null;
    }

    @Override
    public void seleccionarPrimerJugador(int valor) {
    }

    @Override
    public Ronda obtenerRondaActual() {
        return null;
    }

    @Override
    public void finalizarRonda() {
    }

    @Override
    public int obtenerCantidadDeRondasJugadas() {
        return 0;
    }

    @Override
    public int obtenerCantidadDeTurnosJugados() {
        return 0;
    }

    @Override
    public void setRonda(Ronda unaRonda) {
    }

    @Override
    public boolean leTocaALPrimerJugador() {
        return false;
    }

    @Override
    public void finalizarTurnoActual() {

    }


}
