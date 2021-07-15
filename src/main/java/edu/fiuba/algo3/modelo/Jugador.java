package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {
    private String color = "";
    private int cantidadPaisesDominados = 0;
    private Objetivo secreto;
    private Objetivo general = new General();


    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return cantidadPaisesDominados;
    }

    public void colocarEjercitos(int cantidadDeEjercitos, Pais pais) throws PaisOcupadoPorOtroJugadorException {
        pais.colocarEjercito(new Ejercito(this, cantidadDeEjercitos));
        cantidadPaisesDominados++;
    }

    public void asignarColor(String color) {
        this.color = color;
    }

    public Objetivo obtenerObjetivoSecreto() {
        return this.secreto;
    }

    public Objetivo obtenerObjetivoGeneral() {
        return this.general;
    }

    public ArrayList<Dado> tirarDados(int cantidadEjercitos) {
        ArrayList<Dado> dados = new ArrayList<>();
        if (cantidadEjercitos > 3) cantidadEjercitos = 3;

        for (int i = 0; i < cantidadEjercitos; i++) {
            Dado unDado = new Dado();
            unDado.lanzar();
            dados.add(unDado);
        }
        return dados;
    }

}
