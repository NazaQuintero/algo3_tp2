package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class BatallaTest {

    @Test
    public void ArgentinaAtacaABrasilCon3EjercitosYGanaLaBatallaColocando1EjercitoEnBrasil() {
        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        Jugador jugadorDefensorMock = mock(Jugador.class);


        Dados dadosAtacanteMock = mock(Dados.class); // serian dados que le pedimos a los jugadores
        Dados dadosDefensorMock = mock(Dados.class);

        ArrayList<Integer> valoresResultado = new ArrayList<>(Arrays.asList(1,1,1));

        when(dadosAtacanteMock.compararDadosCon(dadosDefensorMock)).thenReturn(valoresResultado);

        when(jugadorAtacanteMock.tirarDados(3)).thenReturn(dadosAtacanteMock);
        when(jugadorDefensorMock.tirarDados(3)).thenReturn(dadosDefensorMock);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock, 5);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock, 3);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, 3, paisDefensor);
        unaBatalla.batallar(dadosAtacanteMock, dadosDefensorMock);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jugadorAtacanteMock, paisDefensor.dominadoPor());
    }

}
