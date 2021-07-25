package edu.fiuba.algo3.modelo.batallasDeDados;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.PaisIndefinido;

import java.util.Random;

public class DadoRandom implements Dado {
    private int valorTirada;
    private Pais pais;

    public DadoRandom() {
        this.pais = new PaisIndefinido("indefinido");
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

    public void asignarPais(Pais pais) {
        this.pais = pais;
    }

    public Pais obtenerPais() {
        return pais;
    }
}