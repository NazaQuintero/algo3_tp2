package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.objetivos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetivosTest {

    @Test
    public void porDefectoUnJugadorNoCumpleNingunObjetivo() {
        Jugador jugador = new Jugador();

        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleElObjetivoGeneralDe30Paises() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        for (int i = 0; i < 30; i++) jugador.colocarEjercitos(new Pais(i + ""));

        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleObjetivoDeDestruccion() {
        Jugador jugador = new Jugador();
        Jugador otroJugador = new Jugador();

        jugador.asignarObjetivo(new Destruccion(otroJugador));
        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorNoCumpleObjetivoDeDestruccion() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Jugador jugador = new Jugador();
        Jugador otroJugador = new Jugador();
        otroJugador.colocarEjercitos(new Pais("Japon"));

        jugador.asignarObjetivo(new Destruccion(otroJugador));
        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void porDefectoOcupacionDeAfrica5DeAmericaDelNortey4DeEuropaNoEstaCumplido() {
        Objetivo ocupacion1 = new Ocupacion1();

        assertEquals("Ocupar África, 5 países de América del Norte y 4 países de Europa.", ocupacion1.obtenerDescripcion());
        assertFalse(ocupacion1.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSur7DeEuropay3LimitrofesNoEstaCumplido() {
        Objetivo ocupacion2 = new Ocupacion2();

        assertEquals("Ocupar América del Sur, 7 países de Europa y 3 países limítrofes entre sí en cualquier lugar del mapa.", ocupacion2.obtenerDescripcion());
        assertFalse(ocupacion2.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeAsiaY2DeAmericaDelSurNoEstaCumplido() {
        Objetivo ocupacion3 = new Ocupacion3();

        assertEquals("Ocupar Asia y 2 países de América del Sur.", ocupacion3.obtenerDescripcion());
        assertFalse(ocupacion3.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeEuropa4DeAsiay2DeAmericaDelSurNoEstaCumplido() {
        Objetivo ocupacion4 = new Ocupacion4();

        assertEquals("Ocupar Europa, 4 países de Asia y 2 países de América de Sur.", ocupacion4.obtenerDescripcion());
        assertFalse(ocupacion4.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelNorte2DeOceaniaY4DeAsiaNoEstaCumplido() {
        Objetivo ocupacion5 = new Ocupacion5();

        assertEquals("Ocupar América del Norte, 2 países de Oceanía y 4 de Asia.", ocupacion5.obtenerDescripcion());
        assertFalse(ocupacion5.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDe2DeOceania2DeAfrica2DeAmericaDelSur3DeEuropa4DeAmericaDeLNorteY3DeAsiaNoEstaCumplido() {
        Objetivo ocupacion6 = new Ocupacion6();

        assertEquals("Ocupar 2 países de Oceanía, 2 países de África, 2 países de América del Sur, 3 países de Europa, 4 de América del Norte y 3 de Asia.", ocupacion6.obtenerDescripcion());
        assertFalse(ocupacion6.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeOceaniaAmericaDelNorteY2DeEuropaNoEstaCumplido() {
        Objetivo ocupacion7 = new Ocupacion7();

        assertEquals("Ocupar Oceanía, América del Norte y 2 países de Europa.", ocupacion7.obtenerDescripcion());
        assertFalse(ocupacion7.estaCumplido(new Jugador()));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSurAfricaY5DeAmericaDelNorteNoEstaCumplido() {
        Objetivo ocupacion8 = new Ocupacion8();

        assertEquals("Ocupar América del Sur, África y 5 países de América del Norte.", ocupacion8.obtenerDescripcion());
        assertFalse(ocupacion8.estaCumplido(new Jugador()));
    }

    @Test
    public void ocupacion2AmericaDelSur7DeEuropay3LimitrofesEstaCumplido() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        MultitonContinentes.reiniciar();
        Continente americaDelSur = MultitonContinentes.obtenerInstanciaDe("America del Sur");
        Continente europa = MultitonContinentes.obtenerInstanciaDe("Europa");

        ArrayList<Pais> paisesDeEuropa = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            paisesDeEuropa.add(new Pais(i+""));
            europa.agregarPais(paisesDeEuropa.get(i));
        }

        Jugador jugador = new Jugador();
        jugador.asignarObjetivo(new Ocupacion2());

        for(Pais pais: paisesDeEuropa) jugador.colocarEjercitos(pais);

        Pais argentina = new Pais("Argentina");
        Pais uruguay = new Pais("Uruguay");
        Pais chile = new Pais("Chile");

        americaDelSur.agregarPais(argentina);
        americaDelSur.agregarPais(uruguay);
        americaDelSur.agregarPais(chile);

        argentina.limitaCon(uruguay);
        uruguay.limitaCon(argentina);

        argentina.limitaCon(chile);
        chile.limitaCon(argentina);

        jugador.colocarEjercitos(argentina);
        jugador.colocarEjercitos(uruguay);
        jugador.colocarEjercitos(chile);

        assertTrue(europa.dominaCantidadDePaises(jugador, 7));
        assertTrue(americaDelSur.dominadoPor(jugador));

        assertTrue(jugador.poseeTresPaisesLimitrofes());

        assertTrue(jugador.cumpleObjetivo());

    }

}
