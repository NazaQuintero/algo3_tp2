package edu.fiuba.algo3.modelo.canjes;

public class TercerCanje extends Canje {

    @Override
    public Canje siguienteCanje() { return new NesimoCanje(); }

    @Override
    public int cantidadEjercitos() {
        if (canjeActivado) return 0;
        canjeActivado = true;
        return 10;
    }

}
