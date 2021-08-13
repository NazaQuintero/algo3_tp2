package edu.fiuba.algo3.modelo.paises;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Jugadores;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;

import java.util.*;


public class RepartidorDePaises {

    public static void repartirPaises(Jugadores jugadores) {

        int cantidadJugadores = jugadores.obtenerCantidad();
        ArrayList<Pais> paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());

        Collections.shuffle(paises);

        try {
            for (int i = 0; i < paises.size(); i++) {
                Jugador jugador = jugadores.obtenerJugador(i % cantidadJugadores);
                jugador.colocarEjercitos(paises.get(i), 1);
            }
        } catch (Exception ignored) {}
    }

}