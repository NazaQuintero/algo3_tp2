package edu.fiuba.algo3.modelo;

public interface Fichas {
    Jugador obtenerJugador();
    int obtenerCantidad();
    void modificarCantidad(int unaCantidad);

    int pedirCantidadAlJugador();
}
