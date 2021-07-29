package edu.fiuba.algo3.modelo.canjes;

public class CanjeNulo extends Canje {

    @Override
    public Canje siguienteCanje() { return new PrimerCanje(); }

    @Override
    public int cantidadEjercitos() { return 0; }
}
