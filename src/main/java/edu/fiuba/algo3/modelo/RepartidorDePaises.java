package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;

import java.util.*;


public class RepartidorDePaises {

    public void repartirPaises(Jugadores jugadores) {
        ArrayList<Pais> paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());
        Collections.shuffle(paises);
        int cantidadJugadores = jugadores.obtenerCantidad();

        try {
            for (int i = 0; i < paises.size(); i++) {
                Jugador jugador = jugadores.obtenerJugador(i % cantidadJugadores);
                jugador.colocarEjercitos(paises.get(i), 1);
            }
        } catch (ElJugadorNoTieneTurnoException | NoEsRondaDeColocacionException | JugadorNoExisteException | PaisOcupadoPorOtroJugadorException | NoQuedanMasEjercitosPorColocarException ignored) {
        }
    }

}