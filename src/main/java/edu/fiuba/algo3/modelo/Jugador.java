package edu.fiuba.algo3.modelo;

public class Jugador {

    private final String color;
    private int cantidadPaisesDominados = 0;

    public Jugador(String color) {
        this.color = color;
    }

    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return cantidadPaisesDominados;
    }

}
