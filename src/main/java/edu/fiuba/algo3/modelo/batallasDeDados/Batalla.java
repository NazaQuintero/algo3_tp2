package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.Pais;

public class Batalla  {

    private Pais atacante;
    private Pais defensor;

    public Batalla(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
    }

    public void batallar() {
        ResultadoBatalla resultado = new ResultadoBatalla(atacante, defensor);
        resultado.computarResultado();
        resultado.procesarResultado();
        // si solo recibe el jugador, adentro se pide la cantidad de ejercitos que se desea pasar a este pais
    }

}

