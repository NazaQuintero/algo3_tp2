package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.victorias.Victoria;

import java.util.ArrayList;

public class ResultadoBatalla {
    private ArrayList<Victoria> resultados;

    public ResultadoBatalla(Dados dadosAtacante, Dados dadosDefensor) {
        this.resultados = new ArrayList<>();
        this.computarResultado(dadosAtacante, dadosDefensor);
    }

    private void computarResultado(Dados dadosAtacante, Dados dadosDefensor) {
        int tamanioMenor = Math.min(dadosDefensor.obtenerCantidad(), dadosAtacante.obtenerCantidad());
        for(int i = 0; i < tamanioMenor; i++) {
            Dado atacante = dadosAtacante.obtenerDado(i);
            Dado defensor = dadosDefensor.obtenerDado(i);
            resultados.add(ArbitroDeDado.obtenerInstancia().obtenerResultado(atacante, defensor));
        }
    }

    public ArrayList<Victoria> obtenerResultados() {
        return this.resultados;
    }

}
