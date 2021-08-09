package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.Ronda;

public interface Turno extends Subject {

    int obtenerCantidadDeTurnosPorRonda();

    Jugador obtenerJugadorTurnoActual();

    void seleccionarPrimerJugador(int valor) throws JugadorNoExisteException;

    Ronda obtenerRondaActual();

    int obtenerCantidadDeRondasJugadas();

    int obtenerCantidadDeTurnosJugados();

    void setRonda(Ronda unaRonda);

    boolean leTocaALPrimerJugador();

    void finalizarTurnoActual();

    void finalizarRonda(Jugador jugador) throws ElJugadorNoTieneTurnoException;

    Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException, EjercitosInsuficientesException, ElPaisNoEsLimitrofeException;

    void reagrupar(Pais origen, Pais destino, int cantidad) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException;

    void colocarEjercitos(Pais pais, int cantidadEjercitos) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException;

    void activarTarjeta(Tarjeta buscarTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException;

}
