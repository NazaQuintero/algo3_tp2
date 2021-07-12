package edu.fiuba.algo3.modelo;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private ArrayList<Pais> paises;

    public void agregarPais(Pais unPais) {
        paises.add(unPais);
    }

    public void repartirPaises(ArrayList<Jugador> jugadores) {
        Random r = new Random();
        int indicePaisAleatorio = r.nextInt(jugadores.size())+1;
    }