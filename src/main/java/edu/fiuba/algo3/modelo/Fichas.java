package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public interface Fichas {
    Jugador obtenerJugador();
    int obtenerCantidad();
    void modificarCantidad(int unaCantidad);

    int pedirCantidadAlJugador();

    ArrayList<Dado> tirarDados(Pais pais);

    void rolAtacante();

    void rolDefensor();
}
