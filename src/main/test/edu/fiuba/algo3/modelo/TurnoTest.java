package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TurnoTest {

    @Test
    public void laCantidadDeTurnosEs3siLaCantidadDeJugadoresEs3() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(3, turno.obtenerCantidadDeTurnosPorRonda());
    }

    @Test
    public void porDefectoElTurnoEsDelJugadorNumero1() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alSeleccionarPrimerJugadorAlJugador2EsElJugadorDelTurnoActual() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
//        Dado unDado = new Dado(); Posible solucion para seleccionar un jugador de manera aleatoria
//        unDado.lanzar();
//        unDado.obtenerValor();
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElAtaqueDelJugador2ElTurnoSigueSiendoDeJugador2() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        jugador2.finalizarRonda();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Reagrupe", ronda.obtenerDescripcion());
    }

    @Test
    public void alFinalizarElTurnoDelJugador3ElTurnoDelSiguienteJugadorEsDelJugador1() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void porDefectoLaCantidadDeTurnosJugadosEsCero() {
        Jugadores jugadores = new Jugadores();

        Turno turno = new ConTurno(jugadores);

        assertEquals(0, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void luegoDeQueElJugador2TerminoDeReagruparLeTocaRondaDeAtaqueAlJugador3() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Ataque", ronda.obtenerDescripcion());
    }

    @Test
    public void luegoDeQueElTodosAtacaronYDefendieronTocaColocacion() {
        Jugadores jugadores = new Jugadores();

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarRonda();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        jugador1.finalizarRonda();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        jugador1.finalizarRonda();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Colocacion", ronda.obtenerDescripcion());
    }


    @Test
    public void luegoDeFinalizar3TurnosLaCantidadDeTurnosJugadosEs3() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        jugador2.finalizarRonda();
        jugador2.finalizarRonda();
        assertEquals(jugador3.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        jugador3.finalizarRonda();
        jugador3.finalizarRonda();
        jugador1.finalizarRonda();
        jugador1.finalizarRonda();
        assertEquals(3, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void porDefectoLaPrimerRondaEsDeAtaque() {
        Jugadores jugadores = new Jugadores();

        Turno turno = new ConTurno(jugadores);
        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLeTocaNuevamenteAlPrimerJugadorLaRondaEsDeColocacion() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        jugador2.finalizarRonda();
        jugador2.finalizarRonda();

        jugador3.finalizarRonda();
        jugador3.finalizarRonda();

        jugador1.finalizarRonda();
        jugador1.finalizarRonda();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Colocacion", unaRonda.obtenerDescripcion());
    }

    @Test
    public void cuandoLaRondaEsDeReagrupePorPrimeraVezLaCantidadDeRondasJugadasEs0() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        jugador2.finalizarRonda();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());

        jugador2.finalizarRonda();

        assertEquals(0, turno.obtenerCantidadDeRondasJugadas());

        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Ataque", ronda.obtenerDescripcion());
    }

    @Test
    public void cuandoLeTocaAlPrimerJugadorPorTerceraVezLaRondaEsDeAtaque() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        turno.finalizarRonda();
        turno.finalizarRonda();

        turno.finalizarRonda();
        turno.finalizarRonda();

        turno.finalizarRonda();
        turno.finalizarRonda();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        assertEquals("Colocacion", turno.obtenerRondaActual().obtenerDescripcion());

        turno.finalizarRonda();
        turno.finalizarRonda();
        turno.finalizarRonda();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertEquals(2, turno.obtenerCantidadDeRondasJugadas());
    }

}
