package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RondaTest {
    @Test
    public void unJugadorNoPuedeAtacarSiNoTieneTurno() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Usuario usuario3 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        Jugador jugador3 = new Jugador(3, usuario3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais oootroPais = new Pais("Bolivia");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));
        oootroPais.colocarEjercito(new Ejercito(jugador3)); // se coloca 1 solo ejercito en cada pais

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertNotEquals(jugador2, turno.obtenerJugadorTurnoActual());
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> { jugador2.atacarA(otroPais, unPais); });

    }

    @Test
    public void elJugadorConTurnoNoPuedeAtacarSiLaRondaNoEsDeAtaque() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        jugador1.finalizarRonda(); // aca finaliza la ronda de ataque

        Ronda unaRonda = turno.obtenerRondaActual();
        assertNotEquals("Ataque", unaRonda.obtenerDescripcion());
        assertEquals("Reagrupe", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeAtaqueException.class, () -> { jugador1.atacarA(unPais, otroPais); });

    }


    @Test
    public void elJugadorConTurnoNoPuedeReagruparSiLaRondaNoEsDeReagrupe() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        Ronda unaRonda = turno.obtenerRondaActual();

        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertNotEquals("Reagrupe", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeReagrupeException.class, () -> { jugador1.reagrupar(unPais, otroPais); });

    }

    @Test
    public void elJugadorConTurnoNoPuedeColocarEjercitosSiLaRondaNoEsDeColocacion() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        Ronda unaRonda = turno.obtenerRondaActual();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertNotEquals("Colocacion", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeColocacionException.class, () -> { jugador1.colocarEjercitos(unPais); });

    }


    @Test
    public void rondaConDosJugadoresNoSeAtacanYSeColocan2NuevosEjercitosCadaUno() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Usuario usuarioMock1 = mock(Usuario.class);
        Usuario usuarioMock2 = mock(Usuario.class);
        when(usuarioMock1.pedirCantidad()).thenReturn(2);
        when(usuarioMock2.pedirCantidad()).thenReturn(2);
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1, usuarioMock1); // ahora cada jugador recibe un objeto usuario, si fuera online tendria mas comportamiento asi q esta bueno (?
        Jugador jugador2 = new Jugador(2, usuarioMock2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chile");


        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2)); // esto seria previo al comienzo de la partida obviamente

        // a este punto cada pais tiene 1 ejercito de cada jugador

        assertEquals(1, unPais.cantidadEjercitos());
        assertEquals(jugador1, unPais.dominadoPor());
        assertEquals(1, otroPais.cantidadEjercitos());
        assertEquals(jugador2, otroPais.dominadoPor());

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // seleccionamos a jugador1 como primer jugador

        jugador1.finalizarRonda(); // no ataca
        jugador1.finalizarRonda(); // no reagrupa
        jugador2.finalizarRonda(); // no ataca
        jugador2.finalizarRonda(); // no reagrupa

        // le toca nuevamente al jugador1, ronda de colocacion
        assertEquals("Colocacion", unTurno.obtenerRondaActual().obtenerDescripcion());
        assertEquals(jugador1, unTurno.obtenerJugadorTurnoActual());

        jugador1.colocarEjercitos(unPais); // coloca 2 ejercitos
        jugador1.finalizarRonda();

        jugador2.colocarEjercitos(otroPais); // coloca 2 ejercitos
        jugador2.finalizarRonda();

        assertEquals(3, unPais.cantidadEjercitos());
        assertEquals(3, otroPais.cantidadEjercitos());
    }

}
