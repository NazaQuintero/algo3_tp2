package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.excepciones.ActivacionTarjetaEnRondaEquivocadaException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.rondas.Ronda;

public class SinTurno implements Turno {
    @Override
    public int obtenerCantidadDeTurnosPorRonda() {
        return 0;
    }

    @Override
    public Jugador obtenerJugadorTurnoActual() {
        return null;
    }

    @Override
    public void seleccionarPrimerJugador(int valor) {
    }

    @Override
    public Ronda obtenerRondaActual() {
        return null;
    }

    @Override
    public void finalizarRonda() {
    }

    @Override
    public int obtenerCantidadDeRondasJugadas() {
        return 0;
    }

    @Override
    public int obtenerCantidadDeTurnosJugados() {
        return 0;
    }

    @Override
    public void setRonda(Ronda unaRonda) {
    }

    @Override
    public boolean leTocaALPrimerJugador() {
        return false;
    }

    @Override
    public void finalizarTurnoActual() { }

    @Override
    public void atacarA(Pais atacante, Pais defensor) throws ElJugadorNoTieneTurnoException {
        throw new ElJugadorNoTieneTurnoException();
    }

    @Override
    public void reagrupar(Pais origen, Pais destino) throws ElJugadorNoTieneTurnoException {
        throw new ElJugadorNoTieneTurnoException();
    }

    @Override
    public void colocarEjercitos(Pais unPais) throws ElJugadorNoTieneTurnoException {
        throw new ElJugadorNoTieneTurnoException();
    }

    @Override
    public void activarTarjeta(Tarjeta buscarTarjeta) throws ElJugadorNoTieneTurnoException {
        throw new ElJugadorNoTieneTurnoException();
    }
}
