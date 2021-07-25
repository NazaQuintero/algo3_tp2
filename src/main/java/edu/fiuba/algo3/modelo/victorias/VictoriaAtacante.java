package edu.fiuba.algo3.modelo.victorias;

import edu.fiuba.algo3.modelo.batallasDeDados.Dado;
import edu.fiuba.algo3.modelo.fichas.Ejercito;

public class VictoriaAtacante implements Victoria {

    private final Dado atacante;
    private final Dado defensor;

    public VictoriaAtacante(Dado atacante, Dado defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    @Override
    public void procesarVictoria() {
        if(defensor.obtenerPais().cantidadEjercitos() > 0) {
            defensor.obtenerPais().modificarCantidadEjercito(-1);
        }
        if(defensor.obtenerPais().cantidadEjercitos() <= 0) {
            defensor.obtenerPais().dominadoPor().quitarPais(defensor.obtenerPais());
            defensor.obtenerPais().colocarEjercito(new Ejercito(atacante.obtenerPais().dominadoPor()));
        }
    }
}
