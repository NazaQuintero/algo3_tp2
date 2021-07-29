package edu.fiuba.algo3.modelo.canjes;

public class NesimoCanje extends Canje {

    private static int cantidadEjercitos = 10;

    public NesimoCanje() { cantidadEjercitos +=  5; }

    @Override
    public Canje siguienteCanje() {
        return new NesimoCanje();
    }

    public static void reiniciar() {
        cantidadEjercitos = 10;
    }

    @Override
    public int cantidadEjercitos() {
        return cantidadEjercitos;
    }
}
