package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Dados {

    private ArrayList<Integer> valores;

     public ArrayList<Integer> obtenerValores() {
        return this.valores;
    }

    public Dados(int cantidad) {
        valores = new ArrayList<>();
        Random r = new Random();
        int valorTirada;
        if (cantidad > 3) cantidad = 3;
        for (int i = 0; i < cantidad; i++) {
            valorTirada = 1 + r.nextInt(5) + 1;
            valores.add(valorTirada);
        }
        this.valores.sort(Comparator.reverseOrder());
    }

    private int cantidadDados() {
        return valores.size();
    }

    public ArrayList<Integer> compararDadosCon(Dados defensor) {
        // Itero por la collection de menor tamanio
        // Comparo valores 1 a 1 (Pre: las collections estan oredenadas de Mayor a menor)
        ArrayList<Integer> resultado = new ArrayList<>(); // Creo Lista de resultado tamanio del menor con la comparacion
        int tamanioResultado;
        if(this.cantidadDados() > defensor.cantidadDados()) tamanioResultado = defensor.cantidadDados();
        else tamanioResultado = this.cantidadDados();

        for (int i = 0; i < tamanioResultado; i++) {
            resultado.add(valores.get(i) - defensor.valores.get(i)); // > 0 gana atacante, = 0 empate, < 0 gana defensor
        }
        return resultado;
    }
}
