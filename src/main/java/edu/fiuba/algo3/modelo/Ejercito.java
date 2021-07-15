package edu.fiuba.algo3.modelo;

public class Ejercito implements Fichas {

    private Jugador jugador;
    private int cantidad;

    public Ejercito(Jugador jugador, int cantidad) {
        this.jugador = jugador;
        this.cantidad = cantidad;
    }

    public void modificarCantidad(int nuevaCantidad) {
        this.cantidad += nuevaCantidad;
    }

    public Jugador obtenerJugador() {
        return jugador;
    }

    public int obtenerCantidad() {
        return cantidad;
    }
}
