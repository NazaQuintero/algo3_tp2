package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Defensor implements Rol {
    public Dados tirarDados(Pais pais) {
        int cantidadEjercitosAtacante = pais.cantidadEjercitos();
        Dados dados = new Dados(cantidadEjercitosAtacante);
        if(cantidadEjercitosAtacante >= 3) cantidadEjercitosAtacante = 3;
        // t0d0 esto podría estar en un método o clase validadore

        while(dados.obtenerCantidad() != cantidadEjercitosAtacante )
            dados.removerUnDado();
        dados.lanzar();

        return dados;
    }
}
