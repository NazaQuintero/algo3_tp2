package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void porDefectoUnPaisNoEstaDominado() {

        Pais unPais = new Pais("Argentina");

        assertNull(unPais.dominadoPor());
    }

    @Test
    public void porDefectoUnPaisNoTieneEjercitos() {

        Pais unPais = new Pais("Argentina");

        assertEquals(0, unPais.cantidadEjercitos());
    }

    @Test
    public void laCantidadDeEjercitosColocadaEsCorrecta() throws PaisOcupadoPorOtroJugadorException {

        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(3, new Jugador());
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void alColocarseEjercitosEstaDominadoPorUnJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1, unJugador);

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisQueTenia1EjercitoPasaATener4SiElMismoJugadorVuelveAColocar() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1, unJugador);

        assertEquals(unPais.cantidadEjercitos(), 1);

        unPais.colocarEjercitos(3, unJugador);

        assertEquals(4, unPais.cantidadEjercitos());
    }

    @Test
    public void noSePuedeColocarEjercitoEnUnPaisQueEstaDominadoPorOtroJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1, unJugador);


        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unPais.colocarEjercitos(3, otroJugador));
    }

    @Test
    public void dosPaisesPorDefectoNoSonLimitrofes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        assertFalse(unPais.esLimitrofeCon(otroPais));

        unPais.limitaCon(otroPais); // aca establecemos que son limitrofes

        assertTrue(unPais.esLimitrofeCon(otroPais));

    }

    @Test
    public void ataqueEntrePaisesGanaDefensor() throws PaisOcupadoPorOtroJugadorException {

        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(3, new Jugador());

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercitos(3, new Jugador());

        ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(2, 2, 2));
        paisAtacante.atacarA(paisDefensor, dadosAtacante);

        assertEquals(0, paisAtacante.cantidadEjercitos());

    }


    @Test
    public void ataqueEntrePaisesGanaAtacante() throws PaisOcupadoPorOtroJugadorException {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercitos(5, new Jugador());

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercitos(3, new Jugador());

        ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(6, 6, 6));
        paisAtacante.atacarA(paisDefensor, dadosAtacante);

        assertEquals(0, paisDefensor.cantidadEjercitos());

        paisDefensor.colocarEjercitos(1, paisAtacante.dominadoPor());
        assertEquals(paisAtacante.dominadoPor(), paisDefensor.dominadoPor());


    }
}
