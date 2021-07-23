package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.Pais;

public class VictoriaDefensor implements Victoria {

    @Override
    public void procesarVictoria(Pais atacante, Pais defensor) {
        if(atacante.cantidadEjercitos() > 0) {
            atacante.modificarCantidadEjercito(-1);
        }
    }
}
