package edu.fiuba.algo3.modelo;
import java.util.Random;
import java.util.Comparator;

public class Dado implements Comparable<Dado> {
    private int valorTirada;

    public Dado() {
        valorTirada = 0;
    }

    public void lanzar() {
        Random r = new Random();
        valorTirada = 1 + r.nextInt(6);
    }

    public int obtenerValor() {
        return valorTirada;
    }

    @Override
    public int compareTo(Dado dadoDefensor) {
        return Integer.compare(this.valorTirada, dadoDefensor.valorTirada);
    }

}
