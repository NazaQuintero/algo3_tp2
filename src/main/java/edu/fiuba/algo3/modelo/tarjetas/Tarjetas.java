package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Pais;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Tarjetas {
    private ArrayList<Tarjeta> tarjetasLocas;

    public void agregarTarjeta(Tarjeta unaTarjeta) {
        tarjetasLocas.add(unaTarjeta);
    }

    public boolean contieneTarjetaDePais(Pais unPais) {
        return tarjetasLocas.stream().anyMatch(tarjeta -> tarjeta.obtenerPais() == unPais);
    }

    public Stream<Tarjeta> obtenerTarjetasConSimbolo(Simbolo unSimboloLoco) {
        return tarjetasLocas.stream().filter(tarjeta -> tarjeta.obtenerSimbolo() == unSimboloLoco); // esto no va a funcionar :v
    }

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
}