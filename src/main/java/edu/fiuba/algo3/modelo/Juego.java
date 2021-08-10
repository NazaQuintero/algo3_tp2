package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.MultitonTarjetas;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

import java.util.ArrayList;


public class Juego {

    private static final String ARCHIVO_CONTINENTES = "continentes.json";
    private static final String ARCHIVO_PAISES = "paises.json";
    private static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private final Tablero tablero;
    private final Jugadores jugadores;
    private Turno turno = new SinTurno();

    public Juego() throws ArchivoDeContinentesNoEncontradoException, ArchivoDePaisesNoEncontradoException, ArchivoDeTarjetasNoEncontradoException {
        tablero = new Tablero();
        jugadores = new Jugadores();

        CargarJuego.cargarContinentes(ARCHIVO_CONTINENTES);
        CargarJuego.cargarPaisesLimitrofes(ARCHIVO_PAISES);
        CargarJuego.cargarTarjetas(ARCHIVO_TARJETAS);
    }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        MultitonTarjetas.agregarTarjeta(unaTarjeta);
    }

    public void agregarJugador(Jugador jugador){
        jugadores.agregarJugador(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        if (jugadores.obtenerCantidad() < 2) throw new CantidadDeJugadoresInsuficienteException();
        jugadores.setPrimerJugador(0); // despues hacemos que sea uno aleatorio, pero por ahora el 1
        tablero.repartirPaises(jugadores);
        jugadores.mezclar();
        Objetivos.asignarObjetivos(jugadores);
        turno = new ConTurno(jugadores);
    }

    public void iniciarTurno(){
        this.turno = new ConTurno(jugadores);
    }

    public Ronda getRonda() {
        return turno.obtenerRondaActual();
    }

    public Turno getTurno() {
        return turno;
    }
}
