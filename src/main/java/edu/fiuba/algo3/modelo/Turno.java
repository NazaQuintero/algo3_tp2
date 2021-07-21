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

    void atacarA(Pais atacante, Pais defensor) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException;

    void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException;

    void colocarEjercitos(Pais pais) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException;
}
