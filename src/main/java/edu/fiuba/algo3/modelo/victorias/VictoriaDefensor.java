package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.Pais;

public class VictoriaDefensor implements Victoria {

    private final Pais atacante;

    public VictoriaDefensor(Pais atacante) {
        this.atacante = atacante;
    }

    @Override
    public void procesarVictoria() {
        if(atacante.cantidadEjercitos() > 0) {
            atacante.modificarCantidadEjercito(-1);
        }
    }
}
