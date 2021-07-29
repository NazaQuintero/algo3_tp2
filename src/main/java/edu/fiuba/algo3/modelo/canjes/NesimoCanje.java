package edu.fiuba.algo3.modelo.canjes;

public class NesimoCanje extends Canje {

    private int cantidadEjercitos;

    public NesimoCanje(int cantidadEjercitos) { this.cantidadEjercitos = cantidadEjercitos + 5; }

    @Override
    public Canje siguienteCanje() {
        return new NesimoCanje(this.cantidadEjercitos());
    }

    @Override
    public int cantidadEjercitos() {
        return cantidadEjercitos;
    }
}
