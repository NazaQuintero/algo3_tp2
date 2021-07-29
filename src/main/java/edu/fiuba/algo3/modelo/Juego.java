package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Juego {
    static final String ARCHIVO_PAISES = "paises.json";
    static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private final Tablero tablero;
    private final Jugadores jugadores;
    private final ArrayList<Tarjeta> tarjetas;
    private Turno turno = new SinTurno();

    public Juego() throws ArchivoDePaisesNoEncontradoException, ArchivoDeTarjetasNoEncontradoException{
        tablero = new Tablero();
        jugadores = new Jugadores();
        tarjetas = new ArrayList<>();

        CargarJuego.cargarPaisesAlTablero(tablero, ARCHIVO_PAISES);
        CargarJuego.cargarTarjetas(tablero, ARCHIVO_TARJETAS);
    }

    public void agregarJugador(String nombre){
        Jugador jugador = new Jugador(nombre);
        jugadores.agregarJugador(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        if (jugadores.obtenerCantidad() < 2) throw new CantidadDeJugadoresInsuficienteException();
        tablero.repartirPaises(jugadores);
        jugadores.mezclar();
        Objetivos.asignarObjetivos(jugadores);
        turno = new ConTurno(jugadores);
    }

    public void finalizarRonda(String nombreJugador) throws JugadorNoExisteException, ElJugadorNoTieneTurnoException {
        turno.finalizarRonda(jugadores.obtenerJugador(nombreJugador));
    }

    public void ataque(String jugador, String paisAtacante, String paisDefensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException, JugadorNoExisteException, ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        tablero.ataque(jugadores.obtenerJugador(jugador), paisAtacante, paisDefensor, cantidadEjercitos);
    }

    public void colocarEjercitos(String nombreJugador, String nombrePais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, JugadorNoExisteException{
        tablero.colocarEjercitos(jugadores.obtenerJugador(nombreJugador), nombrePais, cantidadEjercitos);
    }
    public void reagrupar(String nombreJugador, String nombrePaisOrigen, String nombrePaisDestino, int cantidadAMover) throws JugadorNoExisteException, ElPaisNoEsLimitrofeException, NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException {
        tablero.reagrupar(jugadores.obtenerJugador(nombreJugador), nombrePaisOrigen, nombrePaisDestino, cantidadAMover);
    }

    public int cantidadPaisesDominados(String nombre) throws JugadorNoExisteException{
        return tablero.cantidadPaisesDominados(jugadores.obtenerJugador(nombre));
    }

    public void recibirTarjeta(String nombreJugador, String paisTarjeta) throws JugadorNoExisteException {
        tablero.recibirTarjeta(jugadores.obtenerJugador(nombreJugador), paisTarjeta);
    }

    public int cantidadEjercitosEn(String nombrePais) {
        return tablero.cantidadEjercitosEn(nombrePais);
    }

    public String paisDominadoPor(String nombrePais) {
        return tablero.paisDominadoPor(nombrePais).obtenerNombre();
    }

}
