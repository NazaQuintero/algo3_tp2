package edu.fiuba.algo3.modelo;

import java.util.Random;

public class Dado {
    private int valorTirada;

    public void lanzar() {
        Random r = new Random();
        valorTirada = 1 + r.nextInt(6);
    }

    public int obtenerValor() {
        return valorTirada;
    }

}
