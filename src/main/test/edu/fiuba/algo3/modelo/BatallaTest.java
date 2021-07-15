package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class BatallaTest {
// TODO: testear batalla en lugar de pais xd

    /*@Test
    public void ArgentinaAtacaABrasilCon3EjercitosYGanaLaBatallaColocando1EjercitoEnBrasil() {
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
    }*/

}
