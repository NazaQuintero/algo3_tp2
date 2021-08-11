package edu.fiuba.algo3.modelo.canjes;

public class PrimerCanje extends Canje {

    @Override
    public Canje siguienteCanje() { return new SegundoCanje(); }

    @Override
    public int cantidadEjercitos() {
        if (canjeActivado) return 0;
        canjeActivado = true;
        return 4;
    }
}
