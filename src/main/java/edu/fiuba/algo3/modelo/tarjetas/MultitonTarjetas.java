package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.observables.Subject;
import edu.fiuba.algo3.modelo.paises.Pais;

import java.util.*;

public class MultitonTarjetas {

    private static final HashMap<Pais, Tarjeta> tarjetas = new HashMap<>();

    private MultitonTarjetas() {}

    public static void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetas.put(unaTarjeta.getPais(), unaTarjeta);
    }

    public static Tarjeta obtenerTarjeta(Pais unPais) {

        return tarjetas.get(unPais); }

    public static Tarjeta obtenerTarjetaAleatoria() {
        ArrayList<Tarjeta> _tarjetas = new ArrayList<>(tarjetas.values());
        Collections.shuffle(_tarjetas);
        return tarjetas.remove(_tarjetas.get(0).getPais());
    }
}