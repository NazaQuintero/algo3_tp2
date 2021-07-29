package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;

public class TarjetaDesactivada implements EstadoTarjeta {
    public EstadoTarjeta activar(Pais unPais) {
        unPais.modificarCantidadEjercito(2);
        return new TarjetaActivada();
    }

    @Override
    public EstadoTarjeta desactivar() throws LaTarjetaYaEstaDesactivadaException {
        throw new LaTarjetaYaEstaDesactivadaException();
    }

}
