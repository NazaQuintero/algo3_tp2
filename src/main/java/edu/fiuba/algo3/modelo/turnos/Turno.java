package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.batallas_de_dados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.Ronda;

public interface Turno extends Subject {

    int obtenerCantidadDeTurnosPorRonda();

    Jugador obtenerJugadorTurnoActual();

    void seleccionarPrimerJugador(int valor) throws JugadorNoExisteException;

    Ronda obtenerRondaActual();

    int obtenerCantidadDeRondasJugadas();

    int getCantidadDeTurnosJugados();

    void setRonda(Ronda unaRonda);

    boolean leTocaALPrimerJugador();

    void finalizarTurnoActual();

    void finalizarRonda(Jugador jugador) throws ElJugadorNoTieneTurnoException;

    Resultado atacarA(Pais atacante, Pais defensor, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException, CantidadDeEjercitosInValidaException, ElPaisNoEsLimitrofeException;

    void reagrupar(Pais origen, Pais destino, int cantidadEjercitos) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException, CantidadDeEjercitosInValidaException;

    void colocarEjercitos(Pais pais, int cantidadEjercitos) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException, NoQuedanMasEjercitosPorColocarException;

    void activarTarjeta(Tarjeta tarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException;

}
