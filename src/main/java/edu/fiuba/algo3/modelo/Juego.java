package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

public class Juego {

    private static final String ARCHIVO_CONTINENTES_Y_PAISES = "continentesYPaises.json";
    private static final String ARCHIVO_PAISES_LIMITROFES = "paisesLimitrofes.json";
    private static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private final Jugadores jugadores;
    private Turno turno = new SinTurno();

    public Juego() throws ArchivoDeContinentesYPaisesNoEncontradoException, ArchivoDePaisesLimitrofesNoEncontradoException, ArchivoDeTarjetasNoEncontradoException {
        jugadores = new Jugadores();

        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES_Y_PAISES);
        CargarJuego.cargarPaisesLimitrofes(ARCHIVO_PAISES_LIMITROFES);
        CargarJuego.cargarTarjetas(ARCHIVO_TARJETAS);
    }

    public void agregarJugador(Jugador jugador){
        jugadores.agregarJugador(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        if (jugadores.obtenerCantidad() < 2) throw new CantidadDeJugadoresInsuficienteException();

        RepartidorDePaises repartidorDePaises = new RepartidorDePaises();
        jugadores.setPrimerJugador(0); // despues hacemos que sea uno aleatorio, pero por ahora el 1
        repartidorDePaises.repartirPaises(jugadores);
        jugadores.mezclar();
        Objetivos.asignarObjetivos(jugadores);
        turno = new ConTurno(jugadores);
    }

    public Turno getTurno() {
        return turno;
    }

    public boolean estaTerminado() {
        return jugadores.algunoCumpleObjetivo();
    }

}
