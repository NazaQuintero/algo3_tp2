package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RondaTest {
    @Test
    public void unJugadorNoPuedeAtacarSiNoTieneTurno() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        Jugador jugador3 = new Jugador(3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais oootroPais = new Pais("Bolivia");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));
        oootroPais.colocarEjercito(new Ejercito(jugador3));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertNotEquals(jugador2, turno.obtenerJugadorTurnoActual());
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> { jugador2.atacarA(otroPais, unPais); });

    }

    @Test
    public void elJugadorConTurnoNoPuedeAtacarSiLaRondaNoEsDeAtaque() {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
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
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
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
        Jugador jugador1 = new Jugador(1);
        Jugador jugador2 = new Jugador(2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        Ronda unaRonda = turno.obtenerRondaActual();

        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertNotEquals("Colocacion", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeColocacionException.class, () -> { jugador1.colocarEjercitos(otroPais); });

    }


}
