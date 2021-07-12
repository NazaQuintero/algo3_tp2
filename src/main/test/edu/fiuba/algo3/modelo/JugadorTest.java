package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {



    @Test
    public void seCreaUnJugadorConColorRosa() {

        Jugador jugador = new Jugador();
        jugador.asignarColor("rosa");

        assertEquals(jugador.mostrarColor(), "rosa");

    }

    @Test
    public void porDefectoUnJugadorNoTieneColorAsignado() {

        Jugador jugador = new Jugador();

        assertEquals(jugador.mostrarColor(), "");
    }

    @Test
    public void porDefectoUnJugadorNoTienePaises() {

        Jugador jugador = new Jugador();

        assertEquals(jugador.cantidadPaisesDominados(), 0);
    }

    @Test
    public void porDefectoUnjugadorNoTieneObjetivoSecreto() {
        Jugador jugador = new Jugador();

        assertNull(jugador.obtenerObjetivoSecreto());
    }

    @Test
    public void porDefectoUnjugadorTieneObjetivoGeneral() {
        Jugador jugador = new Jugador();

        assertNotNull(jugador.obtenerObjetivoGeneral());
    }

    @Test
    public void porDefectoElObjetivoGeneralNoEstaCumplido() {
        Jugador jugador = new Jugador();
        assertFalse(jugador.obtenerObjetivoGeneral().estaCumplido());
    }



}
