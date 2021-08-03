package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Tarjetas {

    private HashMap<Pais, Tarjeta> tarjetasLocas;

    public Tarjetas() { tarjetasLocas = new HashMap<>(); }

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetasLocas.put(unaTarjeta.obtenerPais(), unaTarjeta);
    }

    public Tarjeta obtenerTarjeta(Pais unPais) { return tarjetasLocas.get(unPais); }

    /*public boolean contieneTarjetaDePais(Pais unPais) {
        return tarjetasLocas.stream().anyMatch(tarjeta -> tarjeta.obtenerPais() == unPais);
    }*/

    /*public Stream<Tarjeta> obtenerTarjetasConSimbolo(Simbolo unSimboloLoco) {
        return tarjetasLocas.stream().filter(tarjeta -> tarjeta.obtenerSimbolo() == unSimboloLoco); // esto no va a funcionar :v
    }*/

    /*public TipoCanje metodoMagico() {
        if (this.hayTresSimbolosIguales()) return new TresSimbolosIguales();
        else if (this.hayTresSimbolosDistintos()) return new TresSimbolosDistintos(); // esto nos falto cambiar
        return new SinCanje(); esto no lo usamos igual
    }*/

    private boolean hayTresSimbolosDistintos() {
        return false;
    }

    private boolean hayTresSimbolosIguales() {
        return false;
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
}