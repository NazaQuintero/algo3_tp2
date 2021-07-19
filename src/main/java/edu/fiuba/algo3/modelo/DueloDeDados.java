package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class DueloDeDados {

    public ArrayList<Integer> iniciarDuelo(ArrayList<Dado> dadosAtacante, ArrayList<Dado> dadosDefensor) {
        ArrayList<Integer> resultado = new ArrayList<>(); // Creo Lista de resultado tamanio del menor con la comparacion
        int tamanioResultado;
        if (dadosAtacante.size() > dadosDefensor.size()) tamanioResultado = dadosDefensor.size();
        else tamanioResultado = dadosAtacante.size();

        for (int i = 0; i < tamanioResultado; i++) {
            resultado.add(dadosAtacante.get(i).obtenerValor() - dadosDefensor.get(i).obtenerValor()); // > 0 gana atacante, = 0 empate, < 0 gana defensor
        }
        return resultado;
    }

}
