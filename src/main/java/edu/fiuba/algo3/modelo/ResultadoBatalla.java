package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class ResultadoBatalla {
    private final Pais atacante;
    private final Pais defensor;
    private ArrayList<Victoria> resultados;

    public ResultadoBatalla(Pais atacante, Pais defensor) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.resultados = new ArrayList<>();
    }

    public void computarResultado() {
        ArrayList<Dado> dadosAtacante = atacante.tirarDados();
        ArrayList<Dado> dadosDefensor = defensor.tirarDados();
        int tamanioMenor = Math.min(dadosDefensor.size(), dadosAtacante.size());
        for(int i = 0; i < tamanioMenor; i++) resultados.add(determinarGanador(dadosAtacante.get(i), dadosDefensor.get(i)));
    }

    public void procesarResultado() {
        for (Victoria victoria : resultados) victoria.procesarVictoria(atacante, defensor);
    }

    private Victoria determinarGanador(Dado dadoAtacante, Dado dadoDefensor) {
        return ((dadoAtacante.compareTo(dadoDefensor) > 0) ? new VictoriaAtacante(): new VictoriaDefensor());
    }
}
