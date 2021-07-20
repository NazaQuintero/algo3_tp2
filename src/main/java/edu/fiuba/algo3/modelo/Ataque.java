package edu.fiuba.algo3.modelo;

public class Ataque implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Ataque";
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Reagrupe();
    }

}
