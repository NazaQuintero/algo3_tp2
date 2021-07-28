package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.objetivos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetivosTest {

    @Test
    public void porDefectoUnJugadorNoCumpleNingunObjetivo() {
        Jugador jugador = new Jugador();

        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleElObjetivoGeneralDe30Paises() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        for (int i = 0; i < 30; i++) jugador.colocarEjercitos(new Pais(i + ""));

        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleObjetivoDeDestruccion() {
        Jugador jugador = new Jugador();
        Jugador otroJugador = new Jugador();

        jugador.asignarObjetivo(new Destruccion(otroJugador));
        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorNoCumpleObjetivoDeDestruccion() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        Jugador otroJugador = new Jugador();
        otroJugador.colocarEjercitos(new Pais("Japon"));

        jugador.asignarObjetivo(new Destruccion(otroJugador));
        assertFalse(jugador.cumpleObjetivo());
    }
}
