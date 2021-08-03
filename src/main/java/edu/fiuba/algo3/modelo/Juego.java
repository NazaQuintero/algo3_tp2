package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.Tarjetas;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import java.util.ArrayList;


public class Juego {
    static final String ARCHIVO_PAISES = "paises.json";
    static final String ARCHIVO_TARJETAS = "tarjetas.json";

    private final Tablero tablero;
    private final Jugadores jugadores;
    private final Tarjetas tarjetas;
    private Turno turno = new SinTurno();

    public Juego() throws ArchivoDePaisesNoEncontradoException, ArchivoDeTarjetasNoEncontradoException{
        tablero = new Tablero();
        jugadores = new Jugadores();
        tarjetas = new Tarjetas();

        CargarJuego.cargarContinentes();
        CargarJuego.cargarPaisesLimitrofes(ARCHIVO_PAISES);
        CargarJuego.cargarTarjetas(this, ARCHIVO_TARJETAS);
    }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetas.agregarTarjeta(unaTarjeta);
    }

    public Jugador agregarJugador(String nombre){
        Jugador jugador = new Jugador(nombre);
        jugadores.agregarJugador(jugador);
        return jugador;
    }

    /*
    public void agregarPais(Pais unPais) {
        tablero.agregarPais(unPais);
    }
    */

    public void agregarJugador(Jugador jugador){
        jugadores.agregarJugador(jugador);
    }

    public void comenzar() throws CantidadDeJugadoresInsuficienteException {
        if (jugadores.obtenerCantidad() < 2) throw new CantidadDeJugadoresInsuficienteException();
        tablero.repartirPaises(jugadores);
        jugadores.mezclar();
        Objetivos.asignarObjetivos(jugadores);
        turno = new ConTurno(jugadores);
    }

    public void finalizarRonda(Jugador jugador) throws ElJugadorNoTieneTurnoException {
        turno.finalizarRonda(jugador);
    }

    public void ataque(Jugador jAtacante, Pais pAtacante, Pais pDefensor, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, EjercitosInsuficientesException, NoEsRondaDeAtaqueException, ElPaisNoEsLimitrofeException {
        tablero.ataque(jAtacante, pAtacante, pDefensor, cantidadEjercitos);
    }

    /*
    public void colocarEjercitos(Jugador jugador, Pais pais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, JugadorNoExisteException, PaisOcupadoPorOtroJugadorException {
        tablero.colocarEjercitos(jugador, pais, cantidadEjercitos);
    }
    */

    public void reagrupar(Jugador jugador, Pais paisOrigen, Pais paisDestino, int cantidadAMover) throws JugadorNoExisteException, ElPaisNoEsLimitrofeException, NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException {
        tablero.reagrupar(jugador, paisOrigen, paisDestino, cantidadAMover);
    }

    public int cantidadPaisesDominados(Jugador jugador){
        return tablero.cantidadPaisesDominados(jugador);
    }

    public void recibirTarjeta(Jugador jugador, Pais paisTarjeta) {
        tarjetas.darTarjeta(jugador, paisTarjeta);
    }

    public int cantidadEjercitosEn(Pais pais) {
        return tablero.cantidadEjercitosEn(pais);
    }

    public Jugador paisDominadoPor(Pais pais) {
        return tablero.paisDominadoPor(pais);
    }

    public void activarTarjeta(Jugador jugador, Pais pais) throws JugadorNoExisteException, ElJugadorNoTieneTurnoException, ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException, TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException {
        tarjetas.activarTarjeta(jugador, pais);
    }

    public void canjearTarjetas(Jugador jugador, ArrayList<Pais> paisesTarjetas) throws JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        tarjetas.canjearTarjetas(jugador, paisesTarjetas);
    }

    // Probablemente lo saquemos o saquemos todos los Strings y pongamos objetos
    // Hoy pregunto como nos conviene laburarlo
    public Jugador obtenerJugador(String nombreJugador) throws JugadorNoExisteException {
        return jugadores.obtenerJugador(nombreJugador);
    }

    public Pais obtenerPais(String nombrePais){
        return tablero.obtenerPais(nombrePais);
    }

    public void iniciarTurno(){
        this.turno = new ConTurno(jugadores);
    }
}
