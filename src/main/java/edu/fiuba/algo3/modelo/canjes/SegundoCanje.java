package edu.fiuba.algo3.modelo.canjes;

public class SegundoCanje extends Canje {

    @Override
    public Canje siguienteCanje() { return new TercerCanje(); }

    @Override
    public int cantidadEjercitos() {
        if (canjeActivado) return 0;
        canjeActivado = true;
        return 7;
    }

}
