package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.Ronda;

public interface Turno {
    int obtenerCantidadDeTurnosPorRonda();
    Jugador obtenerJugadorTurnoActual();
    void seleccionarPrimerJugador(int valor);
    Ronda obtenerRondaActual();
    int obtenerCantidadDeRondasJugadas();
    int obtenerCantidadDeTurnosJugados();

    void setRonda(Ronda unaRonda);

    boolean leTocaALPrimerJugador();

    void finalizarTurnoActual();

    void finalizarRonda();

    Resultado atacarA(Pais atacante, Pais defensor) throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException;

    void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException, ElPaisNoEsLimitrofeException;

    void colocarEjercitos(Pais pais) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException, ElJugadorNoPuedeColocarMasEjercitosException;

    void activarTarjeta(Tarjeta buscarTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException;
}
