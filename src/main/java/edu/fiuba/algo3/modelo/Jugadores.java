package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadorNoExisteException;

import java.util.ArrayList;
import java.util.Collections;
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

    public Jugador obtenerJugador(int indice) throws JugadorNoExisteException {
        if (indice >= jugadores.size()) throw new JugadorNoExisteException();
        return this.jugadores.get(indice);
    }
    public Jugador obtenerJugador(String nombre) throws JugadorNoExisteException {
        return (this.jugadores.stream().filter(jugador -> nombre.equals(jugador.getNombre())).findFirst().orElseThrow(JugadorNoExisteException::new));
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

    public void mezclar(){
        Collections.shuffle(this.jugadores);
    }

    @Override
    public Iterator<Jugador> iterator() {

        return new Iterator<>() {

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
    }

    public boolean algunoCumpleObjetivo() {
        return jugadores.stream().anyMatch(Jugador::cumpleObjetivo);
    }

}
