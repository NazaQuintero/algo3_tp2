package edu.fiuba.algo3.modelo.canjes;

import edu.fiuba.algo3.modelo.excepciones.SinCanjeHabilitadoException;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

import java.util.ArrayList;

public abstract class Canje {

    private TipoCanje tipo;
    protected boolean canjeActivado = false;

    public Canje() {
        this.tipo = new SinCanje();
    }

    public abstract Canje siguienteCanje();

    public abstract int cantidadEjercitos(); // Setear canjeActivado como true

    public Canje habilitarCanje(ArrayList<Tarjeta> tarjetas) throws SinCanjeHabilitadoException {

        TipoCanje tipoAuxiliar = tipo.compararTarjetas(tarjetas);
        Canje nuevoCanje = tipoAuxiliar.canjear(this);
        tipo = tipoAuxiliar;
        return nuevoCanje;

    }
}