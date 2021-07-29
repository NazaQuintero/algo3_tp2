package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException {

        Juego juego = new Juego();
        juego.agregarJugador("Juani");
        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }



    @Test
    public void seColocan1EjercitosEnElMismoPais() throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException, JugadorNoExisteException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException {

        Juego juego = new Juego();

        juego.agregarJugador("Juani");
        juego.agregarJugador("Martin");

        juego.colocarEjercitos("Juani", "Argentina", 1);

        assertEquals(1,juego.cantidadEjercitosEn("Argentina"));
        assertEquals("Juani", juego.paisDominadoPor("Argentina"));

    }


    @Test
    public void seRepartenEquitativamente25PaisesEntreDosJugadores() throws JugadorNoExisteException, CantidadDeJugadoresInsuficienteException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException {
        Juego juego = new Juego();
        juego.agregarJugador("Martin");
        juego.agregarJugador("Juani");
        juego.comenzar();

        assertEquals(25,juego.cantidadPaisesDominados("Martin"));
        assertEquals(25,juego.cantidadPaisesDominados("Juani"));
    }

}
