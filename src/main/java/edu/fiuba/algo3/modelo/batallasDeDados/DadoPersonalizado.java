package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.Pais;

public class DadoPersonalizado implements Dado {
    private final int valor;
    private Pais pais;

    public DadoPersonalizado(int valor) {
        this.valor = valor;
    }

    @Override
    public void lanzar() {

    }

    @Override
    public int obtenerValor() {
        return this.valor;
    }

    @Override
    public int compareTo(Dado otroDado) {
        return Integer.compare(this.obtenerValor(), otroDado.obtenerValor());
    }

    @Override
    public boolean esMayorQue(Dado otroDado) {
        return this.compareTo(otroDado) > 0;
    }

    @Override
    public void asignarPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public Pais obtenerPais() {
        return this.pais;
    }
}
