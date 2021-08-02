package edu.fiuba.algo3.modelo.continentes;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;

import java.util.ArrayList;

public abstract class Continente {
    String nombre;
    ArrayList<Pais> paises;
    int cantidadDeEjercitos;

    public Continente(String nombre){
        this.nombre = nombre;
        paises = new ArrayList<>();
    }
    public void agregarPais(Pais pais){
        this.paises.add(pais);
    }

    public boolean dominadoPor(Jugador jugador) {
        return this.paises.stream().allMatch(pais -> pais.dominadoPor() == jugador);
    }
    public boolean dominaCantidadDePaises(Jugador jugador, int cantidad) {
        return (this.paises.stream().filter(pais -> pais.dominadoPor() == jugador).count()) >= cantidad;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public abstract int obtenerCantidadEjercito();

}
