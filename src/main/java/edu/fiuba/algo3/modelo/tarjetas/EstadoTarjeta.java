package edu.fiuba.algo3.modelo.tarjetas;

import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;

public interface EstadoTarjeta {
    EstadoTarjeta activar(Pais unPais) throws LaTarjetaYaFueActivadaException;
    EstadoTarjeta desactivar() throws LaTarjetaYaEstaDesactivadaException;
}
