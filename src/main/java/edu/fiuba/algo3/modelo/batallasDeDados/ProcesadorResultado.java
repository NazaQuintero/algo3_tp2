package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.victorias.Victoria;

public class ProcesadorResultado {
    private static ProcesadorResultado instancia;

    public static ProcesadorResultado obtenerInstancia() {
        if(instancia == null) instancia = new ProcesadorResultado();
        return instancia;
    }

    public void procesar(ResultadoBatalla resultadoBatalla) {
        for(Victoria victoria: resultadoBatalla.obtenerResultados()) victoria.procesarVictoria();
    }
}
