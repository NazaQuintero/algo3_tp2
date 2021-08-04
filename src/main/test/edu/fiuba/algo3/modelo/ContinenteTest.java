package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.PaisOcupadoPorOtroJugadorException;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ContinenteTest {

    @Test
    public void porDefectoLaCantidadDeEjercitosEsCero() {
        Continente asia = new Continente("Asia");
        assertEquals(0, asia.getCantidadDeEjercitos());
    }

    @Test
    public void laCantidadDeEjercitosEsLaCorrectaLuegoDeSetearleUnaCantidadDeEjercitos() {
        Continente asia = new Continente("Asia");
        asia.setCantidadDeEjercitos(3);
        assertEquals(3, asia.getCantidadDeEjercitos());
    }

    @Test
    public void porDefectoNoEstaDominadoPorNadie() {
        Jugador jugador = new Jugador( "Martin", Color.BLUE);
        Continente asia = new Continente("Asia");
        asia.agregarPais(new Pais("China"));
        assertFalse(asia.dominadoPor(jugador));
    }

    @Test
    public void unJugadorDomina3PaisesDeAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador = new Jugador( "Martin", Color.BLUE);
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china, 1);
        jugador.colocarEjercitos(india, 1);
        jugador.colocarEjercitos(japon, 1);

        Continente asia = new Continente("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertTrue(asia.dominaCantidadDePaises(jugador, 3));
    }

    @Test
    public void unJugadorDominaTodoAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador = new Jugador( "Martin", Color.BLUE);
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china, 1);
        jugador.colocarEjercitos(india, 1);
        jugador.colocarEjercitos(japon, 1);

        Continente asia = new Continente("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertTrue(asia.dominadoPor(jugador));
    }

    @Test
    public void unJugadorNoDominaTodoAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador = new Jugador( "Martin", Color.BLUE);
        Jugador jugador2 = new Jugador( "Frank", Color.BLUE);
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        jugador.colocarEjercitos(china, 1);
        jugador.colocarEjercitos(india, 1);
        jugador2.colocarEjercitos(japon, 1);

        Continente asia = new Continente("Asia");
        asia.agregarPais(china);
        asia.agregarPais(india);
        asia.agregarPais(japon);

        assertFalse(asia.dominadoPor(jugador));
    }

    @Test
    public void luegoDeSetearPaisesGetPaisesDevuelveLosPaisesCorrectos() {
        Pais china = new Pais("China");
        Pais india = new Pais("India");
        Pais japon = new Pais("Japon");

        ArrayList<Pais> paises = new ArrayList<>(Arrays.asList(china, india, japon));

        Continente asia = new Continente("Asia");
        asia.setPaises(paises);

        assertEquals(paises, asia.getPaises());
    }

}
