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
        unPais.colocarEjercito(new Ejercito(new Jugador(),3));
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void alColocarUnEjercitoEstaDominadoPorUnJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        Ejercito unEjercito = new Ejercito(unJugador, 1);
        unPais.colocarEjercito(unEjercito);

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisQueTenia1EjercitoPasaATener4SiElMismoJugadorVuelveAColocar() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(unJugador, 1));

        assertEquals(unPais.cantidadEjercitos(), 1);

        unPais.modificarCantidadEjercito(3);

        assertEquals(4, unPais.cantidadEjercitos());
    }

    /*@Test
    public void noSePuedeColocarEjercitoEnUnPaisQueEstaDominadoPorOtroJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(1, unJugador));


        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unPais.colocarEjercito(3, otroJugador));
    }
    */


    @Test
    public void dosPaisesPorDefectoNoSonLimitrofes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        assertFalse(unPais.esLimitrofeCon(otroPais));

        unPais.limitaCon(otroPais); // aca establecemos que son limitrofes

        assertTrue(unPais.esLimitrofeCon(otroPais));

    }

    /*@Test
    public void ataqueEntrePaisesGanaDefensor() throws PaisOcupadoPorOtroJugadorException {

        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercito(new Ejercito(new Jugador(), 3));

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercito(new Ejercito(new Jugador(), 3));

        //ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(2, 2, 2));

        paisAtacante.atacarA(paisDefensor, new Dados(2,2,2));

        assertEquals(0, paisAtacante.cantidadEjercitos());

    }*/


    @Test
    public void ataqueEntrePaisesGanaAtacante() throws PaisOcupadoPorOtroJugadorException {
        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercito(new Ejercito(new Jugador(), 5));

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercito(new Ejercito(new Jugador(), 3));

        ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(6, 6, 6));
        paisAtacante.atacarA(paisDefensor, dadosAtacante);

        assertEquals(0, paisDefensor.cantidadEjercitos());

        paisDefensor.colocarEjercito(new Ejercito(paisAtacante.dominadoPor(), 1));
        assertEquals(paisAtacante.dominadoPor(), paisDefensor.dominadoPor());


    }
}
