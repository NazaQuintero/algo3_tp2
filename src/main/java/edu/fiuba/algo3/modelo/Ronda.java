package edu.fiuba.algo3.modelo;

public interface Ronda {
    boolean puedeAtacar();
    boolean puedeReagrupar();
    boolean puedeColocar();
    Ronda obtenerSiguiente();
}
