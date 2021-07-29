package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.canjes.Canje;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;

public class SinCanje implements TipoCanje {

    @Override
    public TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas) {
        return tarjetas.get(0).compararSimbolos(tarjetas.get(1), tarjetas.get(2)); // aca me gustaria meter una entidad TARJETAS pero mas adelante
    }

    @Override
    public Canje canjear(Canje canje) throws SinCanjeHabilitadoException {
        throw new SinCanjeHabilitadoException();
    }
}
