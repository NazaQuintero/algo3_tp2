package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.Pais;

public interface Dado extends Comparable<Dado> {
    void lanzar();
    int obtenerValor();
    int compareTo(Dado otroDado);
    boolean esMayorQue(Dado otroDado);
    void asignarPais(Pais pais);
    Pais obtenerPais();
}
