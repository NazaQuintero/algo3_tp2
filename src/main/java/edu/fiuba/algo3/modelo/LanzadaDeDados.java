package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class LanzadaDeDados {

    private ArrayList<Integer> valores;

    public LanzadaDeDados(int cantidad) {
        int valorTirada;
        Random r = new Random();

        valores = new ArrayList<>();

        if(cantidad > 3) cantidad = 3;

        for (int i = 0; i < cantidad; i++) {
            valorTirada = 1 + r.nextInt(5) + 1;
            valores.add(valorTirada);
        }
        this.valores.sort(Comparator.reverseOrder());
    }

    public int cantidadDados() {
        return valores.size();
    }

    public int verValor(int orden) {
        return valores.get(orden);
    }

    public ArrayList<Integer> compararDadosCon(LanzadaDeDados defensor) {
        ArrayList<Integer> resultado = new ArrayList<>(); // Creo Lista de resultado tamanio del menor con la comparacion
        int tamanioResultado;
        if(this.cantidadDados() > defensor.cantidadDados()) tamanioResultado = defensor.cantidadDados();
        else tamanioResultado = this.cantidadDados();

        for (int i = 0; i < tamanioResultado; i++) {
            resultado.add(this.verValor(i) - defensor.verValor(i)); // > 0 gana atacante, = 0 empate, < 0 gana defensor
        }
        return resultado;
    }

}
