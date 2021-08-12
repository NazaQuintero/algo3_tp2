package edu.fiuba.algo3.modelo.canjes;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

public class SinCanje implements TipoCanje {

    @Override
    public TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas) {
        return tarjetas.get(0).compararSimbolos(tarjetas.get(1), tarjetas.get(2));
    }

    @Override
    public Canje canjear(Canje canje) throws SinCanjeHabilitadoException {
        throw new SinCanjeHabilitadoException();
    }

}
