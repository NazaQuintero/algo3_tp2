package edu.fiuba.algo3.modelo.batallasDeDados;


public interface Dado extends Comparable<Dado> {
    void lanzar();
    int obtenerValor();
    int compareTo(Dado otroDado);
    boolean esMayorQue(Dado otroDado);
}
