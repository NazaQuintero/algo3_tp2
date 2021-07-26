package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.victorias.Victoria;
import edu.fiuba.algo3.modelo.victorias.VictoriaAtacante;
import edu.fiuba.algo3.modelo.victorias.VictoriaDefensor;

public class ArbitroDeDado {
    private static ArbitroDeDado instancia;

    private ArbitroDeDado() {
    }

    public static ArbitroDeDado obtenerInstancia() {
        if (instancia == null) instancia = new ArbitroDeDado();
        return instancia;
    }


    public Victoria obtenerResultado(Dado dadoAtacante, Dado dadoDefensor) {
        return (dadoAtacante.esMayorQue(dadoDefensor)) ? new VictoriaAtacante(dadoAtacante, dadoDefensor): new VictoriaDefensor(dadoAtacante, dadoDefensor);
    }
}
