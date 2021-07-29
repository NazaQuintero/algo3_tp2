package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;

import java.util.ArrayList;

public interface TipoCanje {
    TipoCanje compararTarjetas(ArrayList<Tarjeta> tarjetas);
    Canje canjear(Canje canje) throws SinCanjeHabilitadoException;
}
