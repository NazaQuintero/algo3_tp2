package edu.fiuba.algo3.modelo.batallasDeDados;

import edu.fiuba.algo3.modelo.victorias.Victoria;

import java.util.ArrayList;

public class ResultadoBatallaNulo implements Resultado {
    @Override
    public ArrayList<Victoria> obtenerResultados() {
        return null;
    }
}
