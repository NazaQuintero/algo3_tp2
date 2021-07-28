package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;

public interface EstadoTarjeta {
    public EstadoTarjeta activar(Pais unPais) throws LaTarjetaYaFueActivadaException;
    public EstadoTarjeta desactivar() throws LaTarjetaYaEstaDesactivadaException;
}
