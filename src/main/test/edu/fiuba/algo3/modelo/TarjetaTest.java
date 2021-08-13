package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.canjes.CanjeHabilitado;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TarjetaTest {

    @Test
    public void unaTarjetaEstaDesactivadaPorDefecto() {
        Pais unPais = new Pais("Argentina");
        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());

        assertFalse(unaTarjeta.estaActivada());
    }

    @Test
    public void unaTarjetaActivadaNoSePuedeActivarDeNuevo() throws LaTarjetaYaFueActivadaException {
        Pais unPais = new Pais("Argentina");
        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());

        unaTarjeta.activar();

        assertThrows(LaTarjetaYaFueActivadaException.class, unaTarjeta::activar);
    }

    @Test
    public void alCompararUnaTarjetaDeSimboloGloboConOtraTarjetaDeSimboloBarcoResultanDiferentes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());
        Tarjeta otraTarjeta = new Tarjeta(otroPais, new Barco());

        assertFalse(unaTarjeta.coincideSimboloCon(otraTarjeta));
    }

    @Test
    public void elComodinTieneDescripcionCorrecta(){
        Comodin comodin = new Comodin();
        assertEquals("Comodin", comodin.obtenerDescripcion());
    }

    @Test
    public void tarjetaActivadaEstaActivada(){
        TarjetaActivada tarjetaActivada = new TarjetaActivada();
        assertTrue(tarjetaActivada.estaActivada());
    }

    @Test
    public void compararSimbolosConUnComodinEsCanjeHabilitado(){
        Tarjeta tarjeta = new Tarjeta(new Pais("Argentina"), new Globo());
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Israel"), new Comodin());
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Francia"), new Canion());

        assertTrue(tarjeta.compararSimbolos(tarjeta2, tarjeta3) instanceof CanjeHabilitado);
    }

    @Test
    public void compararTresSimbolosDistintosSinComodinHabilitaElCanje(){
        Tarjeta tarjeta = new Tarjeta(new Pais("Argentina"), new Globo());
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Israel"), new Barco());
        Tarjeta tarjeta3 = new Tarjeta(new Pais("Francia"), new Canion());

        assertTrue(tarjeta.compararSimbolos(tarjeta2, tarjeta3) instanceof CanjeHabilitado);
    }

}
