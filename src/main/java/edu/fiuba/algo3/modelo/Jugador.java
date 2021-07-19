package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugador {

    private String color = "";
    private int cantidadPaisesDominados = 0;
    private Objetivo secreto;
    private Objetivo general = new General();
    private Rol rol = new RolIndefinido();

    public String mostrarColor() {
        return color;
    }

    public int cantidadPaisesDominados() {
        return cantidadPaisesDominados;
    }

    public void colocarEjercitos(Pais pais) {
        pais.colocarEjercito(new Ejercito(this));
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

    public Dados tirarDados(Pais pais) {
        return this.rol.tirarDados(pais);
    }

    public int pedirCantidad() {
        return 4; // por ahora, despues se la pedimos al usuario
    }

    public void rolAtacante() {
        rol = new Atacante();
    }

    public void rolDefensor() {
        rol = new Defensor();
    }

}
