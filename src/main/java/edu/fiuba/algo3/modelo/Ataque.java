package edu.fiuba.algo3.modelo;

public class Ataque implements Fase {

    @Override
    public Fase obtenerSiguiente() {
        return new Reagrupe();
    }
}
