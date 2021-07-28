package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

import java.util.ArrayList;
import java.util.Random;


public class Juego {
    static final String ARCHIVO_PAISES = "paises.json";
    static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<Objetivo> objetivos;

    public Juego() {
        tablero = new Tablero();
        jugadores = new ArrayList<>();
        tarjetas = new ArrayList<>();
    }

    public void agregarJugador(Jugador unJugador) {
        jugadores.add(unJugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {

        try {
            CargarJuego.cargarPaisesAlTablero(tablero, ARCHIVO_PAISES);
            CargarJuego.cargarTarjetas(tarjetas, ARCHIVO_TARJETAS, tablero);
            CargarJuego.cargarObjetivos(objetivos);
        }

        //Error al cargar los archivos
        catch (Exception e) { e.printStackTrace(); }

        if (jugadores.size() < 2) throw new CantidadDeJugadoresInsuficienteException();
        else tablero.repartirPaises(jugadores);

        asignarObjetivos();
    }

    private void asignarObjetivos() {

        //for (Jugador jugador: jugadores) jugador.asignarObjetivo(nuevoObjetivo());
    }

    private Objetivo nuevoObjetivo() {
        Random r = new Random();
        return objetivos.remove(r.nextInt(objetivos.size())-1);
    }

}
