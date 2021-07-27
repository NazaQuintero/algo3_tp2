package edu.fiuba.algo3.modelo.canjes;

public class TercerCanje extends Canje {

    @Override
    public Canje siguienteCanje() { return new NesimoCanje(this.cantidadEjercitos()); }

    @Override
    public int cantidadEjercitos() { return 10; }
}
