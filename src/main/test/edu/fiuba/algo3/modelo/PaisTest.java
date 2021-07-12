package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

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
    public void alColocarseEjercitosEstaDominadoPorUnJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1, unJugador);

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisTiene1EjercitoLuegoDeQueSeColoqueEjercitoPorPrimeraVez() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(1, unJugador);

        assertEquals(1, unPais.cantidadEjercitos());
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

}
