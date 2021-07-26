package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.fichas.Ejercito;

public class VictoriaAtacante implements Victoria {

    private final Pais atacante;
    private final Pais defensor;

    public VictoriaAtacante(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    @Override
    public void procesarVictoria() {

        if(defensor.cantidadEjercitos() > 0) {
            defensor.modificarCantidadEjercito(-1);
        }
        if(defensor.cantidadEjercitos() == 0) {
            defensor.dominadoPor().quitarPais(defensor);
            defensor.colocarEjercito(new Ejercito(atacante.dominadoPor()));
        }

    }
}
