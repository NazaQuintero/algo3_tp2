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
    public void finalizarTurnoActual() {

    }

    @Override
    public Ronda obtenerRondaActual() {
        return null;
    }

    @Override
    public void finalizarAtaque() {

    }

    @Override
    public void finalizarReagrupe() {

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
    public void cambiarRonda() {

    }

}
