package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;

import java.util.*;

public class MultitonTarjetas implements Subject {

    private static final HashMap<Pais, Tarjeta> tarjetas = new HashMap<>();
    private final ArrayList<Observer> observers = new ArrayList<>();

    private MultitonTarjetas() {}

    public static void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetas.put(unaTarjeta.obtenerPais(), unaTarjeta);
    }

    public static Tarjeta obtenerTarjeta(Pais unPais) {

        return tarjetas.get(unPais); }

    public static Tarjeta obtenerTarjetaAleatoria() {
        ArrayList<Tarjeta> _tarjetas = new ArrayList<>(tarjetas.values());
        Collections.shuffle(_tarjetas);
        return tarjetas.remove(_tarjetas.get(0).obtenerPais());
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