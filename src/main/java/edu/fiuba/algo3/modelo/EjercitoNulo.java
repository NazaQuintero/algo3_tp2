package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class EjercitoNulo implements Fichas {
    public Jugador obtenerJugador() {
        return null;
    }

    public int obtenerCantidad() {
        return 0;
    }

    public void modificarCantidad(int cantidad) {
    }

    public int pedirCantidadAlJugador() {
        return 0;
    }

    @Override
    public ArrayList<Dado> tirarDados(Pais pais) {
        return null;
    }

    @Override
    public void rolAtacante() {

    }

    @Override
    public void rolDefensor() {

    }

}
