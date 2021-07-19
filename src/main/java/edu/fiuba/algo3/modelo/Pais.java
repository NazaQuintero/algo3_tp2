package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Pais {
    String nombre;
    String continente;
    Fichas ejercito;
    private ArrayList<Pais> paisesLimitrofes;

    public Pais(String nombre) {
        this.nombre = nombre;
        this.paisesLimitrofes = new ArrayList<>();
        this.ejercito = new EjercitoNulo();

    }
    public Pais(String nombre, String continente){
        this.nombre = nombre;
        this.continente = continente;
        this.paisesLimitrofes = new ArrayList<>();
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

    public void atacarA(Pais defensor, int cantidadEjercitos) {
        defensor.recibirAtaque(this, cantidadEjercitos);
    }

    public void recibirAtaque(Pais atacante, int cantidadEjercitos) {
        Batalla nuevaBatalla = new Batalla(atacante, cantidadEjercitos, this);
        nuevaBatalla.batallar();
    }
}
