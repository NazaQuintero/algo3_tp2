package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoExisteException;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TurnoTest {

    @Test
    public void laCantidadDeTurnosEs3siLaCantidadDeJugadoresEs3() {

        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Cami", Color.BLUE);
        Jugador jugador3 = new Jugador( "Naza", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(3, turno.obtenerCantidadDeTurnosPorRonda());
    }

    @Test
    public void porDefectoElTurnoEsDelJugadorNumero1() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Naza", Color.BLUE);
        Jugador jugador2 = new Jugador( "Fran", Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void sePuedeCambiarElJugadorInicial() throws JugadorNoExisteException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Fran", Color.BLUE);
        Jugador jugador2 = new Jugador( "Juani", Color.BLUE);
        Jugador jugador3 = new Jugador( "Cami", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1);
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElAtaqueDelJugador2ElTurnoSigueSiendoDeJugador2() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Juani", Color.BLUE);
        Jugador jugador2 = new Jugador( "Martin", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);
        turno.setRonda(new Ataque());

        turno.finalizarRonda(jugador1);
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElTurnoDelJugador3ElTurnoDelSiguienteJugadorEsDelJugador1() throws JugadorNoExisteException, ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza"  , Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani" , Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(2);

        turno.finalizarRonda(jugador3);

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void porDefectoLaCantidadDeTurnosJugadosEsCero() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador( "Martin", Color.RED));
        Turno turno = new ConTurno(jugadores);
        assertEquals(0, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void luegoDeQueElJugador2TerminoDeReagruparLeTocaRondaDeAtaqueAlJugador3() throws JugadorNoExisteException, ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza", Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1);
        turno.setRonda(new Ataque());

        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador2);

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void luegoDeQueElTodosAtacaronYDefendieronTocaColocacion() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);
        turno.setRonda(new Ataque());

        turno.finalizarRonda(jugador1);
        turno.finalizarRonda(jugador1);

        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador2);

        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Colocacion", ronda.obtenerDescripcion());
    }


    @Test
    public void luegoDeFinalizar3TurnosLaCantidadDeTurnosJugadosEs3() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza", Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        turno.finalizarRonda(jugador1);
        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador3);

        assertEquals(3, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void porDefectoLaPrimerRondaEsDeColocacion() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador( "Martin", Color.BLUE));
        Turno turno = new ConTurno(jugadores);
        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Colocacion", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLeTocaNuevamenteAlPrimerJugadorLaRondaEsDeAtaque() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza", Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        turno.finalizarRonda(jugador1);
        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador3);

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLaRondaEsDeReagrupePorPrimeraVezLaCantidadDeRondasJugadasEs0() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza", Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani", Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.finalizarRonda(jugador1);
        assertEquals(0, turno.obtenerCantidadDeRondasJugadas());
    }

    @Test
    public void despuesDeLaColocacionInicialVieneRondaDeAtaque() throws ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Naza"  , Color.BLUE);
        Jugador jugador3 = new Jugador( "Juani" , Color.BLUE);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        turno.finalizarRonda(jugador1);
        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador3);

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
    }

}
