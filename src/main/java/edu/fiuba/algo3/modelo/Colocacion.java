package edu.fiuba.algo3.modelo;

public class Colocacion implements Ronda {


    @Override
    public String obtenerDescripcion() {
        return "Colocacion";
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Ataque();
    }

}
