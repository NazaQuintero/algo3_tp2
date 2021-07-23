package edu.fiuba.algo3.modelo.fichas;

import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;

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
    public Dados tirarDados(Pais pais) {
        return null;
    }

    @Override
    public void rolAtacante() {
    }

    @Override
    public void rolDefensor() {
    }

    public void agregarPais(Pais pais) {
    }

}
