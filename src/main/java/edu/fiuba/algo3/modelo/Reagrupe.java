package edu.fiuba.algo3.modelo;

public class Reagrupe implements Ronda {


    @Override
    public boolean puedeAtacar() {
        return false;
    }

    @Override
    public boolean puedeReagrupar() {
        return true;
    }

    @Override
    public boolean puedeColocar() {
        return false;
    }

    @Override
    public Ronda obtenerSiguiente() {
        return null;
    }

    @Override
    public void finalizarAtaque() {

    }

    @Override
    public void finalizarReagrupe() {

    }
}
