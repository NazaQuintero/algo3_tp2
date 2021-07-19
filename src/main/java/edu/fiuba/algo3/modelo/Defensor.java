package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Defensor implements Rol {
    public Dados tirarDados(Pais pais) {
        Dados dados = new Dados();
        int cantidadEjercitosAtacante = pais.cantidadEjercitos();
        if(cantidadEjercitosAtacante >= 3) cantidadEjercitosAtacante = 3;
        // t0d0 esto podría estar en un método o clase validadore

        while(dados.obtenerCantidad() != cantidadEjercitosAtacante )
            dados.removerUnDado();
        dados.lanzar();

        return dados;
    }
}
