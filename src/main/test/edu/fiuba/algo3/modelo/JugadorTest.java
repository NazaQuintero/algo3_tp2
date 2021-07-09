package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadorTest {



    @Test
    public void seCreaUnJugadorConColorRosa() {

        Jugador jugador = new Jugador("rosa");

        assertEquals(jugador.mostrarColor(), "rosa");

    }

    @Test
    public void porDefectoUnJugadorNoTienePaises() {

        Jugador jugador = new Jugador("rosa");

        assertEquals(jugador.cantidadPaisesDominados(), 0);

    }



}
