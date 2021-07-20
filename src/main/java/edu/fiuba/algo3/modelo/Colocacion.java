package edu.fiuba.algo3.modelo;

public class Colocacion implements Ronda {
    @Override
    public boolean puedeAtacar() {
        return false;
    }

    @Override
    public boolean puedeReagrupar() {
        return false;
    }

    @Override
    public boolean puedeColocar() {
        return true;
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new AtaqueYReagrupe();
    }

    @Override
    public void finalizarAtaque() {

    }

    @Override
    public void finalizarReagrupe() {

    }
}
