package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.victorias.Victoria;
import edu.fiuba.algo3.modelo.victorias.VictoriaAtacante;
import edu.fiuba.algo3.modelo.victorias.VictoriaDefensor;

import java.util.ArrayList;

public class ResultadoBatalla {
//    private final Pais atacante;
//    private final Pais defensor;
    private ArrayList<Victoria> resultados;

    public ResultadoBatalla(Dados dadosAtacante, Dados dadosDefensor) {
//        this.atacante = atacante;
//        this.defensor = defensor;
        this.resultados = new ArrayList<>();
        this.computarResultado(dadosAtacante, dadosDefensor);
    }

    public void computarResultado(Dados dadosAtacante, Dados dadosDefensor) {
//        Dados dadosAtacante = atacante.tirarDados();
//        Dados dadosDefensor = defensor.tirarDados();
        int tamanioMenor = Math.min(dadosDefensor.obtenerCantidad(), dadosAtacante.obtenerCantidad());
        for(int i = 0; i < tamanioMenor; i++) {
            Dado atacante = dadosAtacante.obtenerDado(i);
            Dado defensor = dadosDefensor.obtenerDado(i);
            resultados.add(determinarGanador(atacante, defensor)); //TODO: hacer que la clase Dados sepa comparar elemento a elemento y devuelva un set de Victoria's
        }
    }

    public void procesarResultado() {
        for (Victoria victoria : resultados) victoria.procesarVictoria();
    }

    private Victoria determinarGanador(Dado dadoAtacante, Dado dadoDefensor) {
        return ((dadoAtacante.esMayorQue(dadoDefensor)) ? new VictoriaAtacante(dadoAtacante, dadoDefensor): new VictoriaDefensor(dadoAtacante, dadoDefensor));
    }
}
