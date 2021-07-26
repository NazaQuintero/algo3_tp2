package edu.fiuba.algo3.modelo.batallasDeDados;


public class DadoPersonalizado implements Dado {
    private final int valor;

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

}
