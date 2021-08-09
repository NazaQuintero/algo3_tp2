package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.victorias.Victoria;
import edu.fiuba.algo3.modelo.victorias.VictoriaAtacante;
import edu.fiuba.algo3.modelo.victorias.VictoriaDefensor;

public class ArbitroDeDado {

    private static ArbitroDeDado instancia;

    public static ArbitroDeDado obtenerInstancia() {
        if (instancia == null) instancia = new ArbitroDeDado();
        return instancia;
    }

    public Victoria obtenerResultado(Pais atacante, Pais defensor, Dado dadoAtacante, Dado dadoDefensor) {
        return (dadoAtacante.esMayorQue(dadoDefensor)) ? new VictoriaAtacante(atacante, defensor) : new VictoriaDefensor(atacante);
    }
}
