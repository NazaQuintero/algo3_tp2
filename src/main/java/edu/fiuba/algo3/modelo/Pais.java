package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pais {

    Jugador jugadorDominante;
    private int cantidadEjercitos;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {

        this.cantidadEjercitos = 0;
        this.paisesLimitrofes = new ArrayList();

    }

    public int cantidadEjercitos() { return this.cantidadEjercitos; }

    public Jugador dominadoPor() {
        return this.jugadorDominante;
    }

    public void colocarEjercitos(int cantidadDeEjercitos, Jugador jugador) {

        if (this.jugadorDominante == null) {
            this.jugadorDominante = jugador;
            this.cantidadEjercitos += cantidadDeEjercitos;
        } else this.cantidadEjercitos += cantidadDeEjercitos;
    }

    public boolean esLimitrofeCon(Pais otroPais) {

        int i = 0;
        boolean encontrado = false;

        while (i < paisesLimitrofes.size() && !encontrado) {
            if (paisesLimitrofes.get(i) == otroPais) encontrado = true;
            else i++;
        }

        return encontrado;
    }

    public void limitaCon(Pais otroPais) { paisesLimitrofes.add(otroPais); }
}
