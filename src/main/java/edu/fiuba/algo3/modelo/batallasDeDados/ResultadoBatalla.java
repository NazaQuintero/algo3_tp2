package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.victorias.Victoria;
import edu.fiuba.algo3.modelo.victorias.VictoriaAtacante;
import edu.fiuba.algo3.modelo.victorias.VictoriaDefensor;

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
        Dados dadosAtacante = atacante.tirarDados();
        Dados dadosDefensor = defensor.tirarDados();
        int tamanioMenor = Math.min(dadosDefensor.obtenerCantidad(), dadosAtacante.obtenerCantidad());
        for(int i = 0; i < tamanioMenor; i++) resultados.add(determinarGanador(dadosAtacante.obtenerDado(i), dadosDefensor.obtenerDado(i))); //TODO: hacer que la clase Dados sepa comparar elemento a elemento y devuelva un set de Victoria's
    }

    public void procesarResultado() {
        for (Victoria victoria : resultados) victoria.procesarVictoria(atacante, defensor);
    }

    private Victoria determinarGanador(Dado dadoAtacante, Dado dadoDefensor) {
        return ((dadoAtacante.compareTo(dadoDefensor) < 0) ? new VictoriaAtacante(): new VictoriaDefensor());
    }
}
