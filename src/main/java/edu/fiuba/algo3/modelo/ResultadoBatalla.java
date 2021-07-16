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

    public void computarResultado(int cantidadDeEjercitos) {
        ArrayList<Dado> dadosAtacante = atacante.tirarDados(cantidadDeEjercitos);
        ArrayList<Dado> dadosDefensor = defensor.tirarDados(defensor.cantidadEjercitos());
        int tamanioMenor = dadosAtacante.size();
        if(dadosDefensor.size() < tamanioMenor) tamanioMenor = dadosDefensor.size();
        for (int i = 0; i < tamanioMenor; i++) resultados.add(dadosAtacante.get(i).determinarGanador(dadosDefensor.get(i)));
    }

    public void procesarResultado() {
        for (Victoria victoria : resultados) victoria.procesarVictoria(atacante, defensor);
    }
}
