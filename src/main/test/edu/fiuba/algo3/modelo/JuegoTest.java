package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() {

        Juego juego = new Juego();

        juego.agregarJugador(new Jugador());

        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }


    @Test
    public void seColocan1EjercitosEnElMismoPais() throws PaisOcupadoPorOtroJugadorException {

        Juego juego = new Juego();
        Jugador jugador = new Jugador();
        Pais pais = new Pais("Argentina");

        juego.agregarJugador(jugador);

        jugador.colocarEjercitos(pais);

        assertEquals(1,pais.cantidadEjercitos());
        assertEquals(pais.dominadoPor(), jugador);

    }

    /*@Test
    public void esTuTurnoDeCodearYYoMeLaRascoOK() {
        Juego juego = new Juego();
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        juego.agregarPais("Brasil");
        juego.agregarPais("Chile");
        juego.agregarPais("Argentina");
        juego.agregarPais("Bolivia");
        juego.agregarPais("Uruguay");
        juego.agregarPais("Paraguay");

        juego.agregarJugador(unJugador);
        juego.agregarJugador(otroJugador);
        juego.repartirPaises();
    }*/

    @Test
    public void seRepartenEquitativamenteSeisPaisesEntreDosJugadores() throws CantidadDeJugadoresInsuficienteException, PaisOcupadoPorOtroJugadorException {
        Juego juego = new Juego();
        Jugador jugadorUno = new Jugador();
        Jugador jugadorDos = new Jugador();
        juego.agregarJugador(jugadorUno);
        juego.agregarJugador(jugadorDos);
        juego.comenzar();

        assertEquals(3,jugadorUno.cantidadPaisesDominados());
        assertEquals(3,jugadorDos.cantidadPaisesDominados());
    }
}
