package edu.fiuba.algo3.modelo;

public class Ataque implements Ronda {

    @Override
    public boolean puedeAtacar() {
        return true;
    }

    @Override
    public boolean puedeReagrupar() {
        return false;
    }

    @Override
    public boolean puedeColocar() {
        return false;
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Reagrupe();
    }

    @Override
    public void finalizarAtaque() {

    }

    @Override
    public void finalizarReagrupe() {

    }
}
