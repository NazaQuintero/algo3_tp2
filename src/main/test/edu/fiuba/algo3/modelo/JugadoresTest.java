package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadorNoExisteException;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadoresTest {

    @Test
    public void siHay3JugadoresLuegoDe3IteracionesVuelveAlPrimero() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Juani", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza",  Color.BLUE);
        Jugador jugador3 = new Jugador( "Cami",  Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        assertEquals(3, jugadores.obtenerCantidad());

        Iterator<Jugador> it =  jugadores.iterator();

        assertEquals(jugador1, it.next());
        assertEquals(jugador2, it.next());
        assertEquals(jugador3, it.next());

        assertEquals(jugador1, it.next());

    }

    @Test
    public void siSeSeteaAlJugador2ComoPrimerJugadorElNextDelIteradorDevuelveEseJugador() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Cami", Color.BLUE);
        Jugador jugador3 = new Jugador( "Naza", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        assertEquals(3, jugadores.obtenerCantidad());

        jugadores.setPrimerJugador(1);

        Iterator<Jugador> it =  jugadores.iterator();

        assertEquals(jugador2, it.next());

    }
}
