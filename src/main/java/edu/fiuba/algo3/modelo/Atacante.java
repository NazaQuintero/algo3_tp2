package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Atacante implements Rol {
    public ArrayList<Dado> tirarDados(Pais pais) {
        ArrayList<Dado> dados = new ArrayList<>();
        int cantidadEjercitosAtacante = pais.pedirCantidadAlJugador(); // mas adelante tenemos que validar que la cantidad ingresada no sea mayor que la cantidad total del pais -1
        if(cantidadEjercitosAtacante > 3) cantidadEjercitosAtacante = 3;
        else if(cantidadEjercitosAtacante == 3) cantidadEjercitosAtacante = 2;
        else cantidadEjercitosAtacante = 1;

        for(int i = 0; i < cantidadEjercitosAtacante; i++) {
            dados.add(new Dado());
        }
        return dados;
    }
}
