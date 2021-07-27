package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.TipoCanje;

import java.util.ArrayList;

public abstract class Canje {

    private TipoCanje tipo;

    public abstract Canje siguienteCanje();

    public abstract int cantidadEjercitos();

    public Canje habilitarCanje(ArrayList<Tarjeta> tarjetas) throws SinCanjeHabilitadoException {
        TipoCanje tipoAuxiliar = tipo.compararTarjetas(tarjetas);
        try {
            Canje nuevoCanje = tipoAuxiliar.canjear(this);
            tipo = tipoAuxiliar;
            return nuevoCanje;
        } catch (SinCanjeHabilitadoException e) { // :thumbs_up:
            throw new SinCanjeHabilitadoException();
        }
    }
}