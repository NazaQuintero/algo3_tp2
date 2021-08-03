package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;

public class TarjetaActivada implements EstadoTarjeta {
    public EstadoTarjeta activar(Pais unPais) throws LaTarjetaYaFueActivadaException {
        throw new LaTarjetaYaFueActivadaException();
    }

    @Override
    public EstadoTarjeta desactivar() {
        return new TarjetaDesactivada();
    }
}
