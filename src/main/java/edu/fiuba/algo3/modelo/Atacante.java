package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Atacante implements Rol {
    public Dados tirarDados(Pais pais) {
        Dados dados = new Dados();
        int cantidadEjercitosAtacante = pais.pedirCantidadAlJugador(); // mas adelante tenemos que validar que la cantidad ingresada no sea mayor que la cantidad total del pais -1
        if(cantidadEjercitosAtacante > 3) cantidadEjercitosAtacante = 3;
        else if(cantidadEjercitosAtacante == 3) cantidadEjercitosAtacante = 2;
        else cantidadEjercitosAtacante = 1;

        while(dados.obtenerCantidad() != cantidadEjercitosAtacante )
            dados.removerUnDado();
        dados.lanzar();
        return dados;
    }
}
