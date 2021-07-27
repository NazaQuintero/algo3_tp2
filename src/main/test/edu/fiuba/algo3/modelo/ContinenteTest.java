package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Asia;
import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ContinenteTest {

    @Test
    public void porDefectoNoEstaDominadoPorNadie() {

        Jugador jugador = new Jugador();
        Continente asia = new Asia("Asia");

        asia.agregarPais(new Pais("China"));

        assertFalse(asia.dominadoPor(jugador));
    }

    @Test
    public void unJugadorDomina3PaisesDeAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china);
        jugador.colocarEjercitos(india);
        jugador.colocarEjercitos(japon);

        Continente asia = new Asia("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertTrue(asia.dominaCantidadDePaises(jugador, 3));
    }

    @Test
    public void unJugadorDominaTodoAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china);
        jugador.colocarEjercitos(india);
        jugador.colocarEjercitos(japon);

        Continente asia = new Asia("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertTrue(asia.dominadoPor(jugador));
    }

    @Test
    public void unJugadorNoDominaTodoAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        Jugador jugador2 = new Jugador();
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china);
        jugador.colocarEjercitos(india);
        jugador2.colocarEjercitos(japon);

        Continente asia = new Asia("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertFalse(asia.dominadoPor(jugador));
    }

}
