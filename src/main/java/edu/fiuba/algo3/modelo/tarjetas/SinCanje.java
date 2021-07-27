package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.canjes.Canje;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;

public class SinCanje implements TipoCanje {

    @Override
    public TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas) {
        return null;
    }

    @Override
    public Canje canjear(Canje canje) throws SinCanjeHabilitadoException {
        throw new SinCanjeHabilitadoException();
    }
}
