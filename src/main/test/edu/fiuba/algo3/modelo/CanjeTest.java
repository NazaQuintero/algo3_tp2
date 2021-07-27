package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.canjes.Canje;
import edu.fiuba.algo3.modelo.canjes.CanjeNulo;
import edu.fiuba.algo3.modelo.excepciones.JugadorSinTarjetasException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CanjeTest {
    @Test
    public void unJugadorSinTarjetasNoPuedeRealizarUnCanje()  {
        Usuario usuarioMock = mock(Usuario.class);
        Jugador unJugador = new Jugador(1, usuarioMock);
        assertThrows(JugadorSinTarjetasException.class, unJugador::solicitarCanje);
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
    public void elCuartoCanjeOtorga15EjercitosExtras() { //otorga poggies!!!1XD
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje

        assertEquals(15, unCanje.cantidadEjercitos()); // el cuarto canje da 15 ejercitos
    }

    @Test
    public void elQuintoCanjeOtorga20EjercitosExtras() { //otorga poggies!!!1XD
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el quinto canje

        assertEquals(20, unCanje.cantidadEjercitos()); // el quinto canje da 20 ejercitos
    }

    @Test
    public void elSEXOCanjeOtorga25EjercitosExtras() { //otorga poggies!!!1XD
        Canje unCanje = new CanjeNulo(); // hasta aca no hubieron canjes
        unCanje = unCanje.siguienteCanje(); // hacemos el primer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el segundo canje
        unCanje = unCanje.siguienteCanje(); // hacemos el tercer canje
        unCanje = unCanje.siguienteCanje(); // hacemos el cuarto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el quinto canje
        unCanje = unCanje.siguienteCanje(); // hacemos el sexo canje

        assertEquals(25, unCanje.cantidadEjercitos()); // el sexo canje da 25 ejercitos 0w0
    }

    // y asi sucesivamente


}