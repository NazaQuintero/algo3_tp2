package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoExisteException;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.SinTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals(0, turno.getCantidadDeTurnosJugados());
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
        assertEquals("Ronda de colocación", ronda.obtenerDescripcion());
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

        assertEquals(3, turno.getCantidadDeTurnosJugados());
    }

    @Test
    public void porDefectoLaPrimerRondaEsDeColocacionInicial() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador( "Martin", Color.BLUE));
        Turno turno = new ConTurno(jugadores);
        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals("Ronda de colocación inicial", unaRonda.obtenerDescripcion());
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
        turno.finalizarRonda(jugador1);
        turno.finalizarRonda(jugador2);
        turno.finalizarRonda(jugador3);

        Ronda unaRonda = turno.obtenerRondaActual();
        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertEquals("Ronda de ataque", unaRonda.obtenerDescripcion());
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
    public void despuesDeLaColocacionInicialVieneLaColocacionSecundaria() throws ElJugadorNoTieneTurnoException {
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
        assertEquals("Ronda de colocación secundaria", unaRonda.obtenerDescripcion());
    }

    @Test
    public void andanLosObservers() {
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador("Martin", Color.RED));
        jugadores.agregarJugador(new Jugador("Naza", Color.BLUE));
        jugadores.agregarJugador(new Jugador("Juani", Color.YELLOW));
        jugadores.agregarJugador(new Jugador("Cami", Color.LIGHTCYAN));
        jugadores.agregarJugador(new Jugador("Fran", Color.GREEN));

        ConTurno turno = new ConTurno(jugadores);

        class ObsPersonalizado implements Observer {
            private int cantidad = 1;

            @Override
            public void update() {
                cantidad += 1;
            }
        }

        ObsPersonalizado obs = new ObsPersonalizado();

        turno.addObserver(obs);

        assertEquals(1, obs.cantidad);
        turno.notifyObservers();
        assertEquals(2, obs.cantidad);
        turno.removeObserver(obs);
        turno.notifyObservers();
        assertEquals(2, obs.cantidad);
    }

    @Test
    public void sinTurnoTieneCeroTurnosPorRonda(){
        SinTurno turno = new SinTurno();
        assertEquals(0, turno.obtenerCantidadDeTurnosPorRonda());
    }

    @Test
    public void sinTurnoObtenerJugadorEsNull(){
        SinTurno turno = new SinTurno();
        assertNull(turno.obtenerJugadorTurnoActual());
    }

    @Test
    public void sinTurnoObtenerRondasJugadasEsCero(){
        SinTurno turno = new SinTurno();
        assertEquals(0, turno.obtenerCantidadDeRondasJugadas());
    }

    @Test
    public void sinTurnoObteneTurnosJugadosEsCero(){
        SinTurno turno = new SinTurno();
        assertEquals(0, turno.getCantidadDeTurnosJugados());
    }

    @Test
    public void sinTurnoObtenerRondaEsNull(){
        SinTurno turno = new SinTurno();
        assertNull(turno.obtenerRondaActual());
    }

    @Test
    public void sinTurnoLeTocaAlPrimerJugadorEsFalse(){
        SinTurno turno = new SinTurno();
        assertFalse(turno.leTocaALPrimerJugador());
    }

    @Test
    public void sinTurnoLanzaElJugadorNoTieneTurnoExceptionConCualquierAccion(){
        SinTurno turno = new SinTurno();
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> turno.reagrupar(null, null, 5));
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> turno.atacarA(null, null, 5));
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> turno.colocarEjercitos(null, 5));
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> turno.activarTarjeta(null));
    }
}
