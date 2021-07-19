package edu.fiuba.algo3.modelo;

public class VictoriaDefensor implements Victoria {

    @Override
    public void procesarVictoria(Pais atacante, Pais defensor) {
        atacante.modificarCantidadEjercito(-1);
    }
}
