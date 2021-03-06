package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;

public class TarjetaDesactivada implements EstadoTarjeta {

    @Override
    public EstadoTarjeta activar(Pais unPais) {
        unPais.modificarCantidadEjercito(2);
        return new TarjetaActivada();
    }

    @Override
    public EstadoTarjeta desactivar() throws LaTarjetaYaEstaDesactivadaException {
        throw new LaTarjetaYaEstaDesactivadaException();
    }

    @Override
    public boolean estaActivada() { return false; }

}
