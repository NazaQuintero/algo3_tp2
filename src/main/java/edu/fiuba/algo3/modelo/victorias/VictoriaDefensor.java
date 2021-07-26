package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.batallasDeDados.Dado;

public class VictoriaDefensor implements Victoria {

    private final Dado atacante;
    private final Dado defensor;

    public VictoriaDefensor(Dado atacante, Dado defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    @Override
    public void procesarVictoria() {
        if(atacante.obtenerPais().cantidadEjercitos() > 0) {
            atacante.obtenerPais().modificarCantidadEjercito(-1);
        }
    }
}
