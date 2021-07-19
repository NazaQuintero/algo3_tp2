package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Defensor implements Rol {
    public ArrayList<Dado> tirarDados(Pais pais) {
        ArrayList<Dado> dados = new ArrayList<>();
        int cantidadEjercitosAtacante = pais.cantidadEjercitos();
        if(cantidadEjercitosAtacante > 3) cantidadEjercitosAtacante = 3; // t0d0 esto podría estar en un método o clase validadore
        else if(cantidadEjercitosAtacante == 3) cantidadEjercitosAtacante = 2;
        else cantidadEjercitosAtacante = 1;

        for(int i = 0; i < cantidadEjercitosAtacante; i++) {
            dados.add(new Dado());
        }

        return dados;
    }
}
