package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInsuficienteException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.PaisOcupadoPorOtroJugadorException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() {

        Juego juego = new Juego();
        juego.agregarJugador("Juani");
        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }


    @Test
    public void seColocan1EjercitosEnElMismoPais() throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException {

        Juego juego = new Juego();

        juego.agregarJugador("Juani");
        juego.agregarJugador("Martin");

        juego.colocarEjercitos("Juani", "Argentina", 1);

        assertEquals(1,juego.cantidadEjercitosEn("Argentina"));
        assertEquals("Juani", juego.paisDominadoPor("Argentina"));

    }


    @Test
    public void seRepartenEquitativamente25PaisesEntreDosJugadores() throws CantidadDeJugadoresInsuficienteException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Juego juego = new Juego();
        juego.agregarJugador("Martin");
        juego.agregarJugador("Juani");
        juego.comenzar();

        assertEquals(25,juego.cantidadPaisesDominados("Martin"));
        assertEquals(25,juego.cantidadPaisesDominados("Juani"));
    }

}
