package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Jugadores;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Ronda;

public class ConTurno implements Turno {

    private final Jugadores jugadores;
    private Jugador actual;
    private Ronda ronda;
    private int cantidadDeTurnosJugados;

    public ConTurno(Jugadores jugadores) {
        this.jugadores = jugadores;
        this.setRonda(new Ataque());
        this.cambiarJugadorActual();
        this.cantidadDeTurnosJugados = 0;
    }

    public int obtenerCantidadDeTurnosPorRonda() {
        return this.jugadores.obtenerCantidad();
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.actual;
    }

    public void seleccionarPrimerJugador(int valor) {
        this.jugadores.setPrimerJugador(valor);
        this.jugadores.obtenerJugador(valor).setTurno(this);
        this.cambiarJugadorActual();
    }

    private void cambiarJugadorActual() {
        this.actual = this.jugadores.obtenerSiguiente();
    }

    public boolean leTocaALPrimerJugador() {
        return this.actual == this.jugadores.getPrimerJugador();
    }

    public void finalizarTurnoActual() {
        this.actual.setTurno(new SinTurno());
        this.cambiarJugadorActual();
        this.actual.setTurno(this);
        this.cantidadDeTurnosJugados++;
    }

    public int obtenerCantidadDeTurnosJugados() {
        return this.cantidadDeTurnosJugados;
    }

    public Ronda obtenerRondaActual() {
        return this.ronda;
    }

    public int obtenerCantidadDeRondasJugadas() {
        return this.cantidadDeTurnosJugados / jugadores.obtenerCantidad();
    }

    public void setRonda(Ronda unaRonda) {
        this.ronda = unaRonda;
    }

    public void finalizarRonda() {
        this.ronda.finalizarRonda(this);
    }

    public Resultado atacarA(Pais atacante, Pais defensor) throws NoEsRondaDeAtaqueException {
        try {
            return this.ronda.atacarA(atacante, defensor);
        } catch (ElPaisNoEsLimitrofeException | EjercitosInsuficientesException e) {
            //pedir en la vista que intente con otro pais
            throw new NoEsRondaDeAtaqueException();
        }
    }

    public void reagrupar(Pais origen, Pais destino) throws NoEsRondaDeReagrupeException, ElPaisNoEsLimitrofeException { // o lo q sea la firma
        try {
            this.ronda.reagrupar(origen, destino);
        } catch (NoEsRondaDeReagrupeException e) {
            throw new NoEsRondaDeReagrupeException();
        }
    }

    public void colocarEjercitos(Pais unPais) throws NoEsRondaDeColocacionException {
        try {
            this.ronda.colocarEjercitos(unPais);
        } catch (NoEsRondaDeColocacionException e) {
            throw new NoEsRondaDeColocacionException();
        }
    }

    public void activarTarjeta(Tarjeta unaTarjeta) throws ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException {
        this.ronda.activarTarjeta(unaTarjeta);
    }

}
