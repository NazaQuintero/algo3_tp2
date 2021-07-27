package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.victorias.Victoria;

import java.util.ArrayList;

public class ResultadoBatalla {
    private final Pais atacante;
    private final Pais defensor;
    private ArrayList<Victoria> resultados;

    public ResultadoBatalla(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.resultados = new ArrayList<>();
        this.computarResultado();
    }

    private void computarResultado() {
        Dados dadosAtacante = atacante.tirarDados();
        Dados dadosDefensor = defensor.tirarDados();

        int tamanioMenor = Math.min(dadosDefensor.obtenerCantidad(), dadosAtacante.obtenerCantidad());
        for(int i = 0; i < tamanioMenor; i++) {
            Dado dadoAtacante = dadosAtacante.obtenerDado(i);
            Dado dadoDefensor = dadosDefensor.obtenerDado(i);
            resultados.add(ArbitroDeDado.obtenerInstancia().obtenerResultado(atacante, defensor, dadoAtacante, dadoDefensor));
        }
    }

    public ArrayList<Victoria> obtenerResultados() {
        return this.resultados;
    }

}
