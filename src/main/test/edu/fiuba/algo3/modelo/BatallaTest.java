package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatallaTest {

    @Test
    public void batallarEntrePaisesGanaAtacanteYColocaUnEjercitoEnElPaisDerrotado() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        when(jugadorAtacanteMock.pedirCantidad()).thenReturn(4);
        Jugador jugadorDefensorMock = mock(Jugador.class);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 5);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 3);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);
        Dado dadoDefensorMock = mock(Dado.class);

        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(1);
        ArrayList<Dado> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);

        ArrayList<Dado> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        unaBatalla.batallar();

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jugadorAtacanteMock, paisDefensor.dominadoPor());

    }

    @Test
    public void batallarEntrePaisesDefensorSacaMayorPuntajeDeDadosYElAtacanteQuedaConUnSoloEjercito() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        when(jugadorAtacanteMock.pedirCantidad()).thenReturn(4);
        Jugador jugadorDefensorMock = mock(Jugador.class);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 4);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 3);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(-1);

        ArrayList<Dado> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);
        dadosAtacante.add(dadoAtacanteMock);

        ArrayList<Dado> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);
        dadosDefensor.add(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        unaBatalla.batallar();

        assertEquals(3, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());

    }

    @Test
    public void batallarEntrePaisesHayEmpateYGanaDefensor() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        when(jugadorAtacanteMock.pedirCantidad()).thenReturn(4);
        Jugador jugadorDefensorMock = mock(Jugador.class);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 2);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 1);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(-1);

        ArrayList<Dado> dadosAtacante = new ArrayList<>();
        dadosAtacante.add(dadoAtacanteMock);

        ArrayList<Dado> dadosDefensor = new ArrayList<>();
        dadosDefensor.add(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        unaBatalla.batallar();

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());

    }

}
