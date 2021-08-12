package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetivosTest {

    private static final String ARCHIVO_CONTINENTES = "continentesYPaises.json";

    @Test
    public void porDefectoUnJugadorNoCumpleNingunObjetivo() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Jugador jugador = new Jugador( "Fran", Color.RED);
        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleElObjetivoGeneralDe30Paises() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException, ArchivoDeContinentesYPaisesNoEncontradoException, NoQuedanMasEjercitosPorColocarException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Jugador jugador = new Jugador( "Cami", Color.RED);
        for (int i = 0; i < 30; i++) jugador.colocarEjercitos(new Pais(i + ""), 1);

        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleObjetivoDeDestruccion() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Jugador jugador1 = new Jugador( "NASA", Color.RED);
        Jugador jugador2 = new Jugador( "Juani", Color.RED);

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertTrue(jugador1.cumpleObjetivo());
    }

    @Test
    public void unJugadorNoCumpleObjetivoDeDestruccion() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException, ArchivoDeContinentesYPaisesNoEncontradoException, NoQuedanMasEjercitosPorColocarException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Jugador jugador1 = new Jugador( "Martin", Color.RED);
        Jugador jugador2 = new Jugador( "NASA", Color.RED);
        jugador2.colocarEjercitos(new Pais("Brasil"), 1);

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertFalse(jugador1.cumpleObjetivo());
    }

    @Test
    public void porDefectoOcupacionDeAfrica5DeAmericaDelNortey4DeEuropaNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion1 = new Ocupacion1();
        try {
            assertFalse(ocupacion1.estaCumplido(new Jugador( "Martin", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSur7DeEuropay3LimitrofesNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion2 = new Ocupacion2();
        try {
            assertFalse(ocupacion2.estaCumplido(new Jugador( "Fran", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAsiaY2DeAmericaDelSurNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion3 = new Ocupacion3();
        try {
            assertFalse(ocupacion3.estaCumplido(new Jugador( "Naza", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeEuropa4DeAsiay2DeAmericaDelSurNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion4 = new Ocupacion4();
        try {
            assertFalse(ocupacion4.estaCumplido(new Jugador( "Cami", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelNorte2DeOceaniaY4DeAsiaNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion5 = new Ocupacion5();
        try {
            assertFalse(ocupacion5.estaCumplido(new Jugador( "Martin", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDe2DeOceania2DeAfrica2DeAmericaDelSur3DeEuropa4DeAmericaDeLNorteY3DeAsiaNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion6 = new Ocupacion6();
        try {
            assertFalse(ocupacion6.estaCumplido(new Jugador( "Martin", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeOceaniaAmericaDelNorteY2DeEuropaNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion7 = new Ocupacion7();
        try {
            assertFalse(ocupacion7.estaCumplido(new Jugador( "Martin", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSurAfricaY5DeAmericaDelNorteNoEstaCumplido() throws ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises(ARCHIVO_CONTINENTES);
        Objetivo ocupacion8 = new Ocupacion8();
        try {
            assertFalse(ocupacion8.estaCumplido(new Jugador( "Paul Walker", Color.RED)));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

}
