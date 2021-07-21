package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Jugadores implements Iterable<Jugador> {

    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private int tamanio;
    private int primerJugador;
    Iterator<Jugador> it = this.iterator();

    public Jugadores() {
        this.tamanio = 0;
        this.primerJugador = 0;
    }

    public int obtenerCantidad() {
        return jugadores.size();
    }

    public void agregarJugador(Jugador unJugador) {
        this.jugadores.add(unJugador);
        this.tamanio = jugadores.size();
    }

    public Jugador obtenerJugador(int indice) {
        return this.jugadores.get(indice); //TODO: Capturar la excepcion de ArrayList
    }

    public void setPrimerJugador(int valor) {
        this.primerJugador = valor;
        this.it = this.iterator();
    }

    public Jugador getPrimerJugador() {
        return this.jugadores.get(this.primerJugador);
    }

    public Jugador obtenerSiguiente() {
        return this.it.next();
    }

    @Override
    public Iterator<Jugador> iterator() {
        Iterator<Jugador> it = new Iterator<Jugador>() {

            private int indiceActual = primerJugador;

            @Override
            public boolean hasNext() {
                return indiceActual < tamanio && jugadores.get(indiceActual) != null;
            }

            @Override
            public Jugador next() {
                if (tamanio == 0) return null;
                if(!hasNext()) indiceActual = 0;
                return jugadores.get(indiceActual++);
            }

        };

        return it;
    }
}
