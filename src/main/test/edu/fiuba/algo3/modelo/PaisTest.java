package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaisTest {

    @Test
    public void porDefectoUnPaisNoEstaDominado() {

        Pais unPais = new Pais("Argentina");

        assertNull(unPais.dominadoPor());
    }

    @Test
    public void porDefectoUnPaisNoTieneEjercitos() {

        Pais unPais = new Pais("Argentina");
        assertEquals(0, unPais.cantidadEjercitos());
    }

    @Test
    public void laCantidadDeEjercitosColocadaEsCorrecta() {

        Pais unPais = new Pais("Argentina");
        Ejercito ejercito = new Ejercito(new Jugador());
        ejercito.modificarCantidad(2);
        unPais.colocarEjercito(ejercito);
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void alColocarUnEjercitoEstaDominadoPorUnJugador() {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        Ejercito unEjercito = new Ejercito(unJugador);
        unPais.colocarEjercito(unEjercito);

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisQueTenia1EjercitoPasaATener4SiElMismoJugadorVuelveAColocar() {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(unJugador));

        assertEquals(1, unPais.cantidadEjercitos());

        unPais.modificarCantidadEjercito(3);

        assertEquals(4, unPais.cantidadEjercitos());
    }

    /*@Test
    public void noSePuedeColocarEjercitoEnUnPaisQueEstaDominadoPorOtroJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(1, unJugador));


        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unPais.colocarEjercito(3, otroJugador));
    }
    */


    @Test
    public void dosPaisesPorDefectoNoSonLimitrofes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        assertFalse(unPais.esLimitrofeCon(otroPais));

        unPais.limitaCon(otroPais); // aca establecemos que son limitrofes

        assertTrue(unPais.esLimitrofeCon(otroPais));

    }

    /*@Test
    public void ataqueEntrePaisesGanaDefensor() throws PaisOcupadoPorOtroJugadorException {

        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercito(new Ejercito(new Jugador(), 3));

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercito(new Ejercito(new Jugador(), 3));

        //ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(2, 2, 2));

        paisAtacante.atacarA(paisDefensor, new Dados(2,2,2));

        assertEquals(0, paisAtacante.cantidadEjercitos());

    }*/


    @Test
    public void ataqueEntrePaisesGanaAtacanteYColocaUnEjercitoEnElPaisDerrotado() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Usuario usuarioMock = mock(Usuario.class);

        when(usuarioMock.pedirCantidad()).thenReturn(4);

        Jugador jugadorAtacante = new Jugador(1, usuarioMock);

        Jugador jugadorDefensor = new Jugador(2, usuarioMock);

        Dado dadoAtacante = new DadoPersonalizado(2);

        Dado dadoDefensor = new DadoPersonalizado(1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);

        dadosAtacante.asignarPais(paisAtacante);

        Dados dadosDefensor = new Dados();

        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);

        dadosDefensor.asignarPais(paisDefensor);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacante);
        ejercitoAtacante.modificarCantidad(4);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensor);
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        ResultadoBatalla resultadoBatalla = paisAtacante.atacarCon(paisDefensor, dadosAtacante, dadosDefensor);
        resultadoBatalla.procesarResultado();

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jugadorAtacante, paisDefensor.dominadoPor());

    }

    @Test
    public void ataqueEntrePaisesGanaDefensor() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacante = new Jugador(1, new Usuario());

        Jugador jugadorDefensor = new Jugador(2, new Usuario());


        Dado dadoAtacante = new DadoPersonalizado(1);
        Dado dadoDefensor = new DadoPersonalizado(6);


        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.asignarPais(paisAtacante);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);

        dadosDefensor.asignarPais(paisDefensor);


        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacante);
        ejercitoAtacante.modificarCantidad(3);

        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensor);
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        ResultadoBatalla resultadoBatalla = paisAtacante.atacarCon(paisDefensor, dadosAtacante, dadosDefensor);
        resultadoBatalla.procesarResultado();

        assertEquals(3, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensor, paisDefensor.dominadoPor());
    }
}
