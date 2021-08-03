package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.excepciones.PaisOcupadoPorOtroJugadorException;
import edu.fiuba.algo3.modelo.objetivos.*;
import edu.fiuba.algo3.modelo.paises.Pais;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjetivosTest {

    @Test
    public void porDefectoUnJugadorNoCumpleNingunObjetivo() {
        CargarJuego.cargarContinentes();
        Jugador jugador = new Jugador( "Fran");
        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleElObjetivoGeneralDe30Paises() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        CargarJuego.cargarContinentes();
        Jugador jugador = new Jugador( "Cami");
        for (int i = 0; i < 30; i++) jugador.colocarEjercitos(new Pais(i + ""), 1);

        assertTrue(jugador.cumpleObjetivo());
    }

    @Test
    public void unJugadorCumpleObjetivoDeDestruccion() {
        CargarJuego.cargarContinentes();
        Jugador jugador1 = new Jugador( "NASA");
        Jugador jugador2 = new Jugador( "Juani");

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertTrue(jugador1.cumpleObjetivo());
    }

    @Test
    public void unJugadorNoCumpleObjetivoDeDestruccion() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        CargarJuego.cargarContinentes();
        Jugador jugador1 = new Jugador( "Martin");
        Jugador jugador2 = new Jugador( "NASA");
        jugador2.colocarEjercitos(new Pais("Brasil"), 1);

        jugador1.asignarObjetivo(new Destruccion(jugador2));
        assertFalse(jugador1.cumpleObjetivo());
    }

    @Test
    public void porDefectoOcupacionDeAfrica5DeAmericaDelNortey4DeEuropaNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion1 = new Ocupacion1();
        try {
            assertFalse(ocupacion1.estaCumplido(new Jugador( "Martin")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSur7DeEuropay3LimitrofesNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion2 = new Ocupacion2();
        try {
            assertFalse(ocupacion2.estaCumplido(new Jugador( "Fran")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAsiaY2DeAmericaDelSurNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion3 = new Ocupacion3();
        try {
            assertFalse(ocupacion3.estaCumplido(new Jugador( "Naza")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeEuropa4DeAsiay2DeAmericaDelSurNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion4 = new Ocupacion4();
        try {
            assertFalse(ocupacion4.estaCumplido(new Jugador( "Cami")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelNorte2DeOceaniaY4DeAsiaNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion5 = new Ocupacion5();
        try {
            assertFalse(ocupacion5.estaCumplido(new Jugador( "Martin")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDe2DeOceania2DeAfrica2DeAmericaDelSur3DeEuropa4DeAmericaDeLNorteY3DeAsiaNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion6 = new Ocupacion6();
        try {
            assertFalse(ocupacion6.estaCumplido(new Jugador( "Martin")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeOceaniaAmericaDelNorteY2DeEuropaNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion7 = new Ocupacion7();
        try {
            assertFalse(ocupacion7.estaCumplido(new Jugador( "Martin")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void porDefectoOcupacionDeAmericaDelSurAfricaY5DeAmericaDelNorteNoEstaCumplido() {
        CargarJuego.cargarContinentes();
        Objetivo ocupacion8 = new Ocupacion8();
        try {
            assertFalse(ocupacion8.estaCumplido(new Jugador( "Paul Walker")));
        } catch (ContinenteInvalidoException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void ocupacion2AmericaDelSur7DeEuropay3LimitrofesEstaCumplido() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException, ContinenteInvalidoException {
//        MultitonContinentes.reiniciar();
//        Continenta americaDelSur = MultitonContinentes.obtenerInstanciaDe("America del Sur");
//        Continenta europa = MultitonContinentes.obtenerInstanciaDe("Europa");
//
//        Jugador jugador = new Jugador( "Martin");
//        jugador.asignarObjetivo(new Ocupacion2());
//
//
//        for(int i = 0; i < 7; i++) {
//            Pais paisEuropa = new Pais(i+"");
//            europa.agregarPais(paisEuropa);
//            jugador.colocarEjercitos(paisEuropa, 1);
//        }
//        Pais arg = new Pais("Argentina");
//        Pais uru = new Pais("Uruguay");
//        Pais chl = new Pais("Chile");
//
//        americaDelSur.agregarPais(arg);
//        americaDelSur.agregarPais(uru);
//        americaDelSur.agregarPais(chl);
//
//        arg.limitaCon(uru);
//        arg.limitaCon(chl);
//
//        jugador.colocarEjercitos(arg, 1);
//        jugador.colocarEjercitos(uru, 1);
//        jugador.colocarEjercitos(chl, 1);
//
//        assertTrue(jugador.cumpleObjetivo());
//
//    }

}
