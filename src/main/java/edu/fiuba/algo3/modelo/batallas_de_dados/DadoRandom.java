package edu.fiuba.algo3.modelo.batallas_de_dados;

import java.util.Random;

public class DadoRandom implements Dado {
    private int valorTirada;

    public DadoRandom() {
        this.lanzar();
    }

    public void lanzar() {
        Random r = new Random();
        this.valorTirada = (1 + r.nextInt(6));
    }

    public int obtenerValor() {
        return valorTirada;
    }

    @Override
    public int compareTo(Dado otroDado) {
        return Integer.compare(this.obtenerValor(), otroDado.obtenerValor());
    }

    @Override
    public boolean esMayorQue(Dado otroDado) {
        return this.compareTo(otroDado) > 0;
    }

}