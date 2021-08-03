package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaEstaDesactivadaException;
import edu.fiuba.algo3.modelo.excepciones.LaTarjetaYaFueActivadaException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import edu.fiuba.algo3.modelo.tarjetas.Barco;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TarjetaTest {
    @Test
    public void unaTarjetaEstaDesactivadaPorDefecto() {
        Pais unPais = new Pais("Argentina");
        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());
        assertThrows(LaTarjetaYaEstaDesactivadaException.class, unaTarjeta::desactivar);
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

}
