package edu.fiuba.algo3.modelo;
import java.util.Random;
import java.util.Comparator;

public class Dado implements Comparable<Dado> {
    private int valorTirada;
    private final int MIN_VALOR = 1;
    private final int MAX_VALOR = 6;

    public Dado() {
        this.setValor(MIN_VALOR);
    }

    public void lanzar() {
        Random r = new Random();
        this.setValor(1 + r.nextInt(MAX_VALOR));
    }

    private void setValor(int valor) {
        if (valor >= MIN_VALOR && valor <= MAX_VALOR) this.valorTirada = valor;
    }

    public int obtenerValor() {
        return valorTirada;
    }

    @Override
    public int compareTo(Dado dadoDefensor) {
        return Integer.compare(this.obtenerValor(), dadoDefensor.obtenerValor());
    }

}
