package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.fichas.Ejercito;

public class VictoriaAtacante implements Victoria {

    @Override
    public void procesarVictoria(Pais atacante, Pais defensor) {
        if(defensor.cantidadEjercitos() > 0) {
            defensor.modificarCantidadEjercito(-1);
        }
        if(defensor.cantidadEjercitos() <= 0) {
            defensor.dominadoPor().quitarPais(defensor);
            defensor.colocarEjercito(new Ejercito(atacante.dominadoPor()));
        }
    }
}
