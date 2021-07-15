package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Pais {

    //Jugador jugadorDominante;
    //private int cantidadEjercitos;
    Fichas ejercito;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {
        this.paisesLimitrofes = new ArrayList<Pais>();
        this.ejercito = new EjercitoNulo();
    }

    public int cantidadEjercitos() { return this.ejercito.obtenerCantidad(); }

    public Jugador dominadoPor() {
        return this.ejercito.obtenerJugador();
    }

    public void colocarEjercito(Fichas unEjercito) {
        this.ejercito = unEjercito;
    }

    public void modificarCantidadEjercito(int unaCantidad) {
        this.ejercito.modificarCantidad(unaCantidad);
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

    // La lista de dados esta ordenada de mayor a menor
    public void atacarA(Pais paisDefensor, ArrayList<Integer> dadosAtacante) {
        paisDefensor.recibirAtaque(dadosAtacante, this);
    }

    // La lista de dados esta ordenada de mayor a menor
    private void recibirAtaque(ArrayList<Integer> dadosAtacante, Pais paisAtacante) {
        ArrayList<Integer> dadosDefensor = new ArrayList<>(Arrays.asList(3, 3, 3));
        int cantidadDeVictoriasDefensor = 0;

        // Tiene que iterar sobre la lista de dados mas chica
        for (int i = 0; i < dadosDefensor.size() ; i++) {
            if(dadosDefensor.get(i) >= dadosAtacante.get(i)) {
                cantidadDeVictoriasDefensor++;
            }

        }

        this.modificarCantidadEjercito(-(dadosDefensor.size() - cantidadDeVictoriasDefensor));
        paisAtacante.modificarCantidadEjercito(-cantidadDeVictoriasDefensor);
    }

    public Fichas obtenerEjercito() {
        return this.ejercito;
    }
}
