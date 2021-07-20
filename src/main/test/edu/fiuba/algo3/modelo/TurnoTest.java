package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TurnoTest {

    @Test
    public void laCantidadDeTurnosEs3siLaCantidadDeJugadoresEs3() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        assertEquals(3, turno.obtenerCantidadDeTurnosPorRonda());
    }

    @Test
    public void porDefectoElTurnoEsDelJugadorNumero1() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alSeleccionarPrimerJugadorAlJugador2EsElJugadorDelTurnoActual() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElTurnoDelJugador2ElTurnoDelSiguienteJugadorEsDelJugador3() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        turno.finalizarTurnoActual();
        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void alFinalizarElTurnoDelJugador3ElTurnoDelSiguienteJugadorEsDelJugador1() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());
        turno.finalizarTurnoActual();
        assertEquals(jugador3, turno.obtenerJugadorTurnoActual());
        turno.finalizarTurnoActual();
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void porDefectoLaCantidadDeTurnosJugadosEsCero() {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Turno turno = new Turno(jugadores);

        assertEquals(0, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void luegoDeFinalizar3TurnosLaCantidadDeTurnosJugadosEs3() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        turno.finalizarTurnoActual();
        assertEquals(3, turno.obtenerCantidadDeTurnosJugados());
    }

    @Test
    public void porDefectoLaPrimerRondaEsDeAtaque() {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        Turno turno = new Turno(jugadores);
        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(true, unaRonda.puedeAtacar());
    }

    @Test
    public void cuandoLeTocaNuevamenteAlPrimerJugadorLaRondaEsDeReagrupe() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(false, unaRonda.puedeAtacar());
        assertEquals(true, unaRonda.puedeReagrupar());
    }

    @Test
    public void cuandoLaRondaEsDeReagrupePorPrimeraVezLaCantidadDeRondasJugadasEs1() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        assertEquals(1, turno.obtenerCantidadDeRondasJugadas());
    }

    @Test
    public void cuandoLeTocaAlPrimerJugadorPorTerceraVezLaRondaEsDeColocacion() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(false, unaRonda.puedeAtacar());
        assertEquals(false, unaRonda.puedeReagrupar());
        assertEquals(true, unaRonda.puedeColocar());
        assertEquals(2, turno.obtenerCantidadDeRondasJugadas());
    }

    @Test
    public void cuandoLeTocaAlPrimerJugadorPorCuartaVezLaRondaEsDeAtaqueNuevamente() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Jugador jugador3 = new Jugador();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);

        Turno turno = new Turno(jugadores);
        turno.seleccionarPrimerJugador(1); // se recibe por parametro un numero random que va de cero a la cantidad de jugadores

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        turno.finalizarTurnoActual();
        assertEquals(jugador2, turno.obtenerJugadorTurnoActual());

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(true, unaRonda.puedeAtacar());
        assertEquals(false, unaRonda.puedeReagrupar());
        assertEquals(false, unaRonda.puedeColocar());
        assertEquals(3, turno.obtenerCantidadDeRondasJugadas());
    }

}
