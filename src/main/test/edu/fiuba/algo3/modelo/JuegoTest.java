package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() {

        Juego juego = new Juego();

        juego.agregarJugador("color");

        assertThrows(CantidadInsuficienteException.class, juego::comenzar);

    }


    @Test
    public void seColocan5EjercitosEnElMismoPais() {

        Juego juego = new Juego();
        Jugador jugador = new Jugador("rosa");
        Pais pais = new Pais("Argentina");

        juego.agregarJugador("rosa");

        jugador.colocarEjercitos(5, pais);

        assertEquals(pais.cantidadEjercitos(), 5);
        assertEquals(pais.dominadoPor(), jugador);

    }

}
