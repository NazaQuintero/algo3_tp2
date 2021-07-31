package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.PaisOcupadoPorOtroJugadorException;
import edu.fiuba.algo3.modelo.objetivos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetivosTest {

    @Test
    public void porDefectoUnJugadorNoCumpleNingunObjetivo() {
        Jugador jugador = new Jugador( "Fran");
        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleElObjetivoGeneralDe30Paises() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador = new Jugador( "Cami");
        for (int i = 0; i < 30; i++) jugador.colocarEjercitos(new Pais(i + ""), 1);

        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleObjetivoDeDestruccion() {
        Jugador jugador1 = new Jugador( "NASA");
        Jugador jugador2 = new Jugador( "Juani");

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertTrue(jugador1.cumpleObjetivo());
    }

    @Test
    public void unJugadorNoCumpleObjetivoDeDestruccion() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        Jugador jugador1 = new Jugador( "Martin");
        Jugador jugador2 = new Jugador( "NASA");
        jugador2.colocarEjercitos(new Pais("Brasil"), 1);

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertFalse(jugador1.cumpleObjetivo());
    }

    @Test
    public void porDefectoOcupacionDeAfrica5DeAmericaDelNortey4DeEuropaNoEstaCumplido() {
        Objetivo ocupacion1 = new Ocupacion1();
        assertFalse(ocupacion1.estaCumplido(new Jugador( "Martin")));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSur7DeEuropay3LimitrofesNoEstaCumplido() {
        Objetivo ocupacion2 = new Ocupacion2();
        assertFalse(ocupacion2.estaCumplido(new Jugador( "Fran")));
    }

    @Test
    public void porDefectoOcupacionDeAsiaY2DeAmericaDelSurNoEstaCumplido() {
        Objetivo ocupacion3 = new Ocupacion3();
    assertFalse(ocupacion3.estaCumplido(new Jugador( "Naza")));
    }

    @Test
    public void porDefectoOcupacionDeEuropa4DeAsiay2DeAmericaDelSurNoEstaCumplido() {
        Objetivo ocupacion4 = new Ocupacion4();
        assertFalse(ocupacion4.estaCumplido(new Jugador( "Cami")));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelNorte2DeOceaniaY4DeAsiaNoEstaCumplido() {
        Objetivo ocupacion5 = new Ocupacion5();
        assertFalse(ocupacion5.estaCumplido(new Jugador( "Martin")));
    }

    @Test
    public void porDefectoOcupacionDe2DeOceania2DeAfrica2DeAmericaDelSur3DeEuropa4DeAmericaDeLNorteY3DeAsiaNoEstaCumplido() {
        Objetivo ocupacion6 = new Ocupacion6();
        assertFalse(ocupacion6.estaCumplido(new Jugador( "Martin")));
    }

    @Test
    public void porDefectoOcupacionDeOceaniaAmericaDelNorteY2DeEuropaNoEstaCumplido() {
        Objetivo ocupacion7 = new Ocupacion7();
        assertFalse(ocupacion7.estaCumplido(new Jugador( "Martin")));
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSurAfricaY5DeAmericaDelNorteNoEstaCumplido() {
        Objetivo ocupacion8 = new Ocupacion8();
        assertFalse(ocupacion8.estaCumplido(new Jugador( "Paul Walker")));
    }

    @Test
    public void ocupacion2AmericaDelSur7DeEuropay3LimitrofesEstaCumplido() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        MultitonContinentes.reiniciar();
        Continente americaDelSur = MultitonContinentes.obtenerInstanciaDe("America del Sur");
        Continente europa = MultitonContinentes.obtenerInstanciaDe("Europa");

        Jugador jugador = new Jugador( "Martin");
        jugador.asignarObjetivo(new Ocupacion2());


        for(int i = 0; i < 7; i++) {
            Pais paisEuropa = new Pais(i+"");
            europa.agregarPais(paisEuropa);
            jugador.colocarEjercitos(paisEuropa, 1);
        }
        Pais arg = new Pais("Argentina");
        Pais uru = new Pais("Uruguay");
        Pais chl = new Pais("Chile");

        americaDelSur.agregarPais(arg);
        americaDelSur.agregarPais(uru);
        americaDelSur.agregarPais(chl);

        arg.limitaCon(uru);
        arg.limitaCon(chl);

        jugador.colocarEjercitos(arg, 1);
        jugador.colocarEjercitos(uru, 1);
        jugador.colocarEjercitos(chl, 1);

        assertTrue(jugador.cumpleObjetivo());

    }

}
