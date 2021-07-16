package edu.fiuba.algo3.modelo;

public class VictoriaAtacante implements Victoria {

    @Override
    public void procesarVictoria(Pais atacante, Pais defensor) {
        defensor.modificarCantidadEjercito(-1);
        if(defensor.cantidadEjercitos() <= 0) defensor.colocarEjercito(new Ejercito(atacante.dominadoPor(), 1));
    }
}
