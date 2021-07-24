package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Pais;

public class Tarjeta {

    private final Pais pais;
    private final Simbolo simbolo;

    public Tarjeta(Pais pais, Simbolo simbolo) {
        this.pais = pais;
        this.simbolo = simbolo;
    }

    public Pais obtenerPais(){ return pais; }

    public String nombrePais() {
        return pais.obtenerNombre();
    }

    public void activar() {
        this.pais.modificarCantidadEjercito(2);
    }

    public Simbolo obtenerSimbolo() {
        return simbolo;
    }
}