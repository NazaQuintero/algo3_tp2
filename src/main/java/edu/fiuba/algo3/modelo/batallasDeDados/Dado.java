package edu.fiuba.algo3.modelo.batallasDeDados;
import java.util.Random;

public class Dado implements Comparable<Dado> {
    private int valorTirada;

    public Dado() { this.lanzar(); }

    public void lanzar() {
        Random r = new Random();
        this.valorTirada = (1 + r.nextInt(6));
    }

    public int obtenerValor() {
        return valorTirada;
    }

    @Override
    public int compareTo(Dado dadoDefensor) {
        return Integer.compare(this.obtenerValor(), dadoDefensor.obtenerValor());
    }

}