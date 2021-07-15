package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Dados {

    private int cantidad;

     public LanzadaDeDados lanzar() {
        return new LanzadaDeDados(cantidad);
    }

    public Dados(int cantidad) {
        this.cantidad = cantidad;
    }

}
