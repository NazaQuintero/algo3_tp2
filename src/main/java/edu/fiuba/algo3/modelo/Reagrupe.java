package edu.fiuba.algo3.modelo;

public class Reagrupe implements Ronda {

    @Override
    public String obtenerDescripcion() {
        return "Reagrupe";
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Colocacion();
    }
}
