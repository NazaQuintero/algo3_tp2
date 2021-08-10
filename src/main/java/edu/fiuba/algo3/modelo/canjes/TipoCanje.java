package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

import java.util.ArrayList;

public interface TipoCanje {
    TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas);
    Canje canjear(Canje canje) throws SinCanjeHabilitadoException;
}
