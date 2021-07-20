package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class AtaqueYReagrupe implements Ronda {
    Ronda ronda;

    public AtaqueYReagrupe() {
        this.ronda = new Ataque();
    }

    @Override
    public boolean puedeAtacar() {
        return this.ronda.puedeAtacar();
    }

    @Override
    public boolean puedeReagrupar() {
        return this.ronda.puedeReagrupar();
    }

    @Override
    public boolean puedeColocar() {
        return false;
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Colocacion();
    }

    @Override
    public void finalizarAtaque() {
        this.ronda = new Reagrupe();
    }

    @Override
    public void finalizarReagrupe() {

    }
}
