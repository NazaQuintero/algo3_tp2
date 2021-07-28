package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Pais;

import java.util.ArrayList;

public class General implements Objetivo {

    private ArrayList<Pais> paisesDominados;

    public General(ArrayList<Pais> paisesDominados) {
        this.paisesDominados = paisesDominados;
    }

    @Override
    public boolean estaCumplido() {
        return this.paisesDominados.size() == 30;
    }
}
