package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Jugadores;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Tarjetas implements Subject {

    private HashMap<Pais, Tarjeta> tarjetas;
    private ArrayList<Observer> observers = new ArrayList<>();

    public Tarjetas() { tarjetas = new HashMap<>(); }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetas.put(unaTarjeta.obtenerPais(), unaTarjeta);
    }

    public Tarjeta obtenerTarjeta(Pais unPais) { return tarjetas.get(unPais); }

    public void repartir(Jugadores jugadores) {
        int i = 0;
        try {
            for (Tarjeta t : tarjetas.values()) {
                this.darTarjeta(jugadores.obtenerJugador(i), t.obtenerPais());
                i++;
                if (i == jugadores.obtenerCantidad()) i = 0;
            }
        } catch (JugadorNoExisteException ignored) {}
    }

    public void darTarjeta(Jugador jugador, Pais paisTarjeta) { jugador.recibirTarjeta(obtenerTarjeta(paisTarjeta)); }

    public void activarTarjeta(Jugador jugador, Pais pais) throws ElJugadorNoTieneTurnoException, ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException, TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException {
        jugador.activarTarjetaPais(pais);
    }

    public void canjearTarjetas(Jugador jugador, ArrayList<Pais> paisesTarjetas) throws JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(obtenerTarjeta(paisesTarjetas.get(0)));
        tarjetas.add(obtenerTarjeta(paisesTarjetas.get(1)));
        tarjetas.add(obtenerTarjeta(paisesTarjetas.get(2)));
        jugador.canjearTarjetas(tarjetas);
    }

    @Override
    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs: observers) obs.update();
    }
}