package edu.fiuba.algo3.modelo.continentes;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.fichas.EjercitoNulo;

import java.util.ArrayList;

public class Continente {
    String nombre;
    ArrayList<Pais> paises;
    int cantidadDeEjercitos;

    public Continente(String nombre) {
        this.nombre = nombre;
        this.paises = new ArrayList<>();
        this.cantidadDeEjercitos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }

    public int getCantidadDeEjercitos() {
        return cantidadDeEjercitos;
    }

    public void setCantidadDeEjercitos(int cantidadDeEjercitos) {
        this.cantidadDeEjercitos = cantidadDeEjercitos;
    }

    public boolean dominadoPor(Jugador jugador) {
        return this.paises.stream().allMatch(pais -> pais.dominadoPor() == jugador);
    }

    public boolean dominaCantidadDePaises(Jugador jugador, int cantidad) {
        return (this.paises.stream().filter(pais -> pais.dominadoPor() == jugador).count()) >= cantidad;
    }

    public void setEjercitosNulos() {
        for(Pais pais: paises) pais.colocarEjercito(new EjercitoNulo());
    }

    public void agregarPais(Pais unPais) {
        this.paises.add(unPais);
    }
}
