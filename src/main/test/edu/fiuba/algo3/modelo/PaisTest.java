package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
    public void laCantidadDeEjercitosColocadaEsCorrecta() throws PaisOcupadoPorOtroJugadorException {

        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(new Jugador(),3));
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void alColocarUnEjercitoEstaDominadoPorUnJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        Ejercito unEjercito = new Ejercito(unJugador, 1);
        unPais.colocarEjercito(unEjercito);

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisQueTenia1EjercitoPasaATener4SiElMismoJugadorVuelveAColocar() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(unJugador, 1));

        assertEquals(unPais.cantidadEjercitos(), 1);

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
    public void ataqueEntrePaisesGanaAtacanteYColocaUnEjercitoEnElPaisDerrotado() throws PaisOcupadoPorOtroJugadorException {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        Jugador jugadorDefensorMock = mock(Jugador.class);


        Dado dadoAtacanteMock = mock(Dado.class); // serian dados que le pedimos a los ju
        when(dadoAtacanteMock.obtenerValor()).thenReturn(6);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoDefensorMock.obtenerValor()).thenReturn(1);

        ArrayList<Dado> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);

        ArrayList<Dado> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(3)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(3)).thenReturn(dadosDefensor);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 5);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 3);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        paisAtacante.atacarA(paisDefensor,3);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jugadorAtacanteMock, paisDefensor.dominadoPor());

    }

    @Test
    public void ataqueEntrePaisesGanaDefensor() {
        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        Jugador jugadorDefensorMock = mock(Jugador.class);


        Dado dadoAtacanteMock = mock(Dado.class); // serian dados que le pedimos a los ju
        when(dadoAtacanteMock.obtenerValor()).thenReturn(1);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoDefensorMock.obtenerValor()).thenReturn(6);

        ArrayList<Dado> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);

        ArrayList<Dado> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(3)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(3)).thenReturn(dadosDefensor);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 4);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 3);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        paisAtacante.atacarA(paisDefensor,3);

        assertEquals(3, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());
    }
}
