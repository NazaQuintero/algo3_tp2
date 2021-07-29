package edu.fiuba.algo3.modelo.canjes;

public class PrimerCanje extends Canje {

    @Override
    public Canje siguienteCanje() { return new SegundoCanje(); }

    @Override
    public int cantidadEjercitos() { return 4; }
}
