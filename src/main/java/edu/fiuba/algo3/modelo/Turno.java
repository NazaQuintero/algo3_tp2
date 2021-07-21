package edu.fiuba.algo3.modelo;

public interface Turno {
    int obtenerCantidadDeTurnosPorRonda();
    Jugador obtenerJugadorTurnoActual();
    void seleccionarPrimerJugador(int valor);
    Ronda obtenerRondaActual();
//    void finalizarAtaque();
//    void finalizarReagrupe();
    int obtenerCantidadDeRondasJugadas();
    int obtenerCantidadDeTurnosJugados();

    void setRonda(Ronda unaRonda);

//    void finalizarColocacion();

    boolean leTocaALPrimerJugador();

    void finalizarTurnoActual();

    void finalizarRonda();
}
