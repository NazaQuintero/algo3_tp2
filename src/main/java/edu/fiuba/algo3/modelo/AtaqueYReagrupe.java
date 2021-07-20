package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class AtaqueYReagrupe implements Ronda {
    Fase fase;

    public AtaqueYReagrupe() {
        this.fase = new Ataque();
    }

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
        return false;
    }

    @Override
    public Ronda obtenerSiguiente() {
        return new Colocacion();
    }
}
