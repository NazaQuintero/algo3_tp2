package edu.fiuba.algo3.modelo;

import java.util.ArrayList;


public class Juego {
    static final String ARCHIVO_PAISES = "paises.json";
    static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;

    public Juego() {
        jugadores = new ArrayList<>();
        tablero = new Tablero();
    }

    public void agregarJugador(Jugador unJugador) {
        jugadores.add(unJugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {

        try {
            CargarJuego.cargarPaisesAlTablero(tablero, ARCHIVO_PAISES);
            //CargarJuego.cargarTarjetas(tarjetas, ARCHIVO_TARJETAS);
        }

        //Error al cargar los archivos
        catch (Exception e) {e.printStackTrace();}

        if (jugadores.size() < 2) throw new CantidadDeJugadoresInsuficienteException();
        else tablero.repartirPaises(jugadores);

    }

}
