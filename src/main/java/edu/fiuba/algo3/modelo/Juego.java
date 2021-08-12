package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.paises.RepartidorDePaises;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Juego {

    private final Jugadores jugadores;
    private Turno turno = new SinTurno();

    public Juego() throws ArchivoDeContinentesYPaisesNoEncontradoException, ArchivoDePaisesLimitrofesNoEncontradoException, ArchivoDeTarjetasNoEncontradoException {
        jugadores = new Jugadores();

        CargarJuego.cargarContinentesYPaises();
        CargarJuego.cargarPaisesLimitrofes();
        CargarJuego.cargarTarjetas();

    }

    public Turno getTurno() {
        return turno;
    }

    public void agregarJugador(Jugador jugador){
        jugadores.agregarJugador(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        if (jugadores.obtenerCantidad() < 2) throw new CantidadDeJugadoresInsuficienteException();

        RepartidorDePaises.repartirPaises(jugadores);
        jugadores.mezclar();
        Objetivos.asignarObjetivos(jugadores);
        turno = new ConTurno(jugadores);

    }

    public boolean estaTerminado() {
        return jugadores.algunoCumpleObjetivo();
    }

}
