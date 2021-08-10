package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.*;

import java.util.*;

public class MultitonTarjetas implements Subject {

    private static HashMap<Pais, Tarjeta> tarjetas = new HashMap<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    private MultitonTarjetas() {}

    public static void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetas.put(unaTarjeta.obtenerPais(), unaTarjeta);
    }

    public Tarjeta obtenerTarjeta(Pais unPais) { return tarjetas.get(unPais); }

    public static Tarjeta obtenerTarjetaAleatoria() {
        ArrayList<Tarjeta> _tarjetas = new ArrayList<>(tarjetas.values());
        Collections.shuffle(_tarjetas);
        return tarjetas.remove(_tarjetas.get(0).obtenerPais());
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