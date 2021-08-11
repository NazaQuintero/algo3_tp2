package edu.fiuba.algo3.modelo.batallas_de_dados;


public interface Dado extends Comparable<Dado> {
    void lanzar();
    int obtenerValor();
    int compareTo(Dado otroDado);
    boolean esMayorQue(Dado otroDado);
}
