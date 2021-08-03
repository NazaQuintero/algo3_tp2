package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.PaisOcupadoPorOtroJugadorException;
import edu.fiuba.algo3.modelo.paises.Pais;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContinenteTest {

    @Test
    public void porDefectoNoEstaDominadoPorNadie() {
        Jugador jugador = new Jugador( "Martin");
        Continente asia = new Continente("Asia");
        asia.agregarPais(new Pais("China"));
        assertFalse(asia.dominadoPor(jugador));
    }

    @Test
    public void unJugadorDomina3PaisesDeAsia() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador = new Jugador( "Martin");
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
        Jugador jugador = new Jugador( "Martin");
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
        Jugador jugador = new Jugador( "Martin");
        Jugador jugador2 = new Jugador( "Frank");
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

}
