package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TurnoTest {

    @Test
    public void laCantidadDeTurnosEs3siLaCantidadDeJugadoresEs3() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
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
//        ArrayList<Jugador> jugadores = new ArrayList<>();
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
//        ArrayList<Jugador> jugadores = new ArrayList<>();
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
//        ArrayList<Jugador> jugadores = new ArrayList<>();
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

        jugador2.finalizarAtaque();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Reagrupe", ronda.obtenerDescripcion());
    }

    @Test
    public void alFinalizarElTurnoDelJugador3ElTurnoDelSiguienteJugadorEsDelJugador1() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
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
        jugador2.finalizarAtaque();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarReagrupe();

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void porDefectoLaCantidadDeTurnosJugadosEsCero() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugadores jugadores = new Jugadores();

        Turno turno = new ConTurno(jugadores);

        assertEquals(0, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void luegoDeQueElJugador2TerminoDeReagruparLeTocaRondaDeAtaqueAlJugador3() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();

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
        jugador2.finalizarAtaque();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarReagrupe();

        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Ataque", ronda.obtenerDescripcion());
    }

    @Test
    public void luegoDeQueElTodosAtacaronYDefendieronTocaColocacion() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugadores jugadores = new Jugadores();

        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarAtaque();

        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        jugador2.finalizarReagrupe();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        jugador1.finalizarAtaque();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        jugador1.finalizarReagrupe();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        Ronda ronda = turno.obtenerRondaActual();
        assertEquals("Colocacion", ronda.obtenerDescripcion());
    }


    @Test
    public void luegoDeFinalizar3TurnosLaCantidadDeTurnosJugadosEs3() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador1.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        turno.finalizarTurnoActual();
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

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
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

        jugador2.finalizarAtaque();

        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());

        jugador2.finalizarReagrupe();

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

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());
        assertEquals("Colocacion", turno.obtenerRondaActual().obtenerDescripcion());

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2.obtenerId(), turno.obtenerJugadorTurnoActual().obtenerId());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertEquals(2, turno.obtenerCantidadDeRondasJugadas());
    }

//    @Test
//    public void cuandoLeTocaAlPrimerJugadorPorCuartaVezLaRondaEsDeAtaqueNuevamente() {
//        ArrayList<Jugador> jugadores = new ArrayList<>();
//        Jugador jugador1 = new Jugador();
//        Jugador jugador2 = new Jugador();
//        Jugador jugador3 = new Jugador();
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        jugadores.add(jugador3);
//
//        Turno turno = new Turno(jugadores);
//        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
//
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
//
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
//
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        turno.finalizarTurnoActual();
//        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
//
//        Ronda unaRonda = turno.obtenerRondaActual();
//        assertEquals(true, unaRonda.puedeAtacar());
//        assertEquals(false, unaRonda.puedeReagrupar());
//        assertEquals(false, unaRonda.puedeColocar());
//        assertEquals(3, turno.obtenerCantidadDeRondasJugadas());
//    }

}
