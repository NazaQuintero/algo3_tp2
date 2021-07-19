package edu.fiuba.algo3.modelo;

public class Tarjeta {

    private final Pais pais;
    private final Simbolo simbolo;

    public Tarjeta(Pais pais, Simbolo simbolo) {
        this.pais = pais;
        this.simbolo = simbolo;
    }

    public Pais getPais() {
        return pais;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }
}
