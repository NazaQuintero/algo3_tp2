package edu.fiuba.algo3.modelo;

public interface Turno {
    int obtenerCantidadDeTurnosPorRonda();
    Jugador obtenerJugadorTurnoActual();
    void seleccionarPrimerJugador(int valor);
    Ronda obtenerRondaActual();
    int obtenerCantidadDeRondasJugadas();
    int obtenerCantidadDeTurnosJugados();

    void setRonda(Ronda unaRonda);

    boolean leTocaALPrimerJugador();

    void finalizarTurnoActual();

    void finalizarRonda();
}
