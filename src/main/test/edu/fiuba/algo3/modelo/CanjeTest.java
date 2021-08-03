package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.canjes.CanjeNulo;
import edu.fiuba.algo3.modelo.canjes.NesimoCanje;
import edu.fiuba.algo3.modelo.excepciones.JugadorSinTarjetasException;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CanjeTest {
    @Test
    public void unJugadorSinTarjetasNoPuedeRealizarUnCanje()  {
        Jugador jugador = new Jugador( "Martin");
        Tarjeta tarjeta = new Tarjeta(new Pais("Japon"), new Globo());
        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(tarjeta);
        assertThrows(JugadorSinTarjetasException.class, () -> jugador.canjearTarjetas(tarjetas));
    }

    @Test
    public void elPrimerCanjeOtorga4EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        assertEquals(4, unCanje.cantidadEjercitos()); // el primer canje da 4 ejercitos
    }

    @Test
    public void elSegundoCanjeOtorga7EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        assertEquals(7, unCanje.cantidadEjercitos()); // el segundo canje da 7 ejercitos
    }

    @Test
    public void elTercerCanjeOtorga10EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        assertEquals(10, unCanje.cantidadEjercitos()); // el tercer canje da 10 ejercitos
    }

    @Test
    public void elCuartoCanjeOtorga15EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje

        assertEquals(15, unCanje.cantidadEjercitos()); // el cuarto canje da 15 ejercitos
    }

    @Test
    public void elQuintoCanjeOtorga20EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        NesimoCanje.reiniciar();
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el quinto canje

        assertEquals(20, unCanje.cantidadEjercitos()); // el quinto canje da 20 ejercitos
    }

    @Test
    public void elSextoCanjeOtorga25EjercitosExtras() {
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        NesimoCanje.reiniciar();
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el quinto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el sexto canje

        assertEquals(25, unCanje.cantidadEjercitos()); // el sexto canje da 25 ejercitos
    }

    // y asi sucesivamente


}