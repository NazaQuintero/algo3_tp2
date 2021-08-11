package edu.fiuba.algo3.modelo.batallas_de_dados;

import edu.fiuba.algo3.modelo.victorias.Victoria;

import java.util.ArrayList;

public class ResultadoBatallaNulo implements Resultado {
    @Override
    public ArrayList<Victoria> obtenerResultados() {
        return null;
    }
}
