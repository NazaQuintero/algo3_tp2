package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TurnoTest {

    @Test
    public void laCantidadDeTurnosEs3siLaCantidadDeJugadoresEs3() {

        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Cami");
        Jugador jugador3 = new Jugador(2, "Naza");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(3, turno.obtenerCantidadDeTurnosPorRonda());
    }

    @Test
    public void porDefectoElTurnoEsDelJugadorNumero1() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Naza");
        Jugador jugador2 = new Jugador(1, "Fran");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void sePuedeCambiarElJugadorInicial() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Fran");
        Jugador jugador2 = new Jugador(1, "Juani");
        Jugador jugador3 = new Jugador(2, "Cami");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1);
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElAtaqueDelJugador2ElTurnoSigueSiendoDeJugador2() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Juani");
        Jugador jugador2 = new Jugador(1, "Martin");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);

        jugador1.finalizarRonda();
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElTurnoDelJugador3ElTurnoDelSiguienteJugadorEsDelJugador1() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(2);

        jugador3.finalizarRonda();
        jugador3.finalizarRonda();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void porDefectoLaCantidadDeTurnosJugadosEsCero() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador(0, "Martin"));
        Turno turno = new ConTurno(jugadores);
        assertEquals(0, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void luegoDeQueElJugador2TerminoDeReagruparLeTocaRondaDeAtaqueAlJugador3() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1);

        jugador2.finalizarRonda();
        jugador2.finalizarRonda();

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void luegoDeQueElTodosAtacaronYDefendieronTocaColocacion() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);

        jugador1.finalizarRonda();
        jugador1.finalizarRonda();

        jugador2.finalizarRonda();
        jugador2.finalizarRonda();

        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Colocacion", ronda.obtenerDescripcion());
    }


    @Test
    public void luegoDeFinalizar3TurnosLaCantidadDeTurnosJugadosEs3() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        jugador1.finalizarRonda();
        jugador1.finalizarRonda();
        jugador2.finalizarRonda();
        jugador2.finalizarRonda();
        jugador3.finalizarRonda();
        jugador3.finalizarRonda();

        assertEquals(3, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void porDefectoLaPrimerRondaEsDeAtaque() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador(0, "Martin"));
        Turno turno = new ConTurno(jugadores);
        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLeTocaNuevamenteAlPrimerJugadorLaRondaEsDeColocacion() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        jugador1.finalizarRonda();
        jugador1.finalizarRonda();

        jugador2.finalizarRonda();
        jugador2.finalizarRonda();

        jugador3.finalizarRonda();
        jugador3.finalizarRonda();

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(jugador1.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        assertEquals("Colocacion", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLaRondaEsDeReagrupePorPrimeraVezLaCantidadDeRondasJugadasEs0() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        jugador1.finalizarRonda();
        assertEquals(0, turno.obtenerCantidadDeRondasJugadas());
    }

    @Test
    public void cuandoLeTocaAlPrimerJugadorPorTerceraVezLaRondaEsDeAtaque() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(2, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);

        turno.finalizarRonda();
        turno.finalizarRonda();

        turno.finalizarRonda();
        turno.finalizarRonda();

        turno.finalizarRonda();
        turno.finalizarRonda();

        // Empieza la ronda de colocacion

        turno.finalizarRonda();
        turno.finalizarRonda();
        turno.finalizarRonda();

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
    }

}
