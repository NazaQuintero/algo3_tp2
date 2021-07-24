package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Batalla;
import edu.fiuba.algo3.modelo.batallasDeDados.Dado;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import org.junit.jupiter.api.Test;

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

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock);
        ejercitoAtacante.modificarCantidad(4);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);
        Dado dadoDefensorMock = mock(Dado.class);

        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(-1);
        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);

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

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock);
        ejercitoAtacante.modificarCantidad(4);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        unaBatalla.batallar();

        assertEquals(3, paisDefensor.cantidadEjercitos());
        assertEquals(2, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());

    }

    @Test
    public void batallarEntrePaisesHayEmpateYGanaDefensor() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");

        Jugador jugadorAtacanteMock = mock(Jugador.class);
        when(jugadorAtacanteMock.pedirCantidad()).thenReturn(4);
        Jugador jugadorDefensorMock = mock(Jugador.class);

        Ejercito ejercitoAtacante = new Ejercito(jugadorAtacanteMock);
        ejercitoAtacante.modificarCantidad(1);
        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        Batalla unaBatalla = new Batalla(paisAtacante, paisDefensor);

        Dado dadoAtacanteMock = mock(Dado.class);
        when(dadoAtacanteMock.obtenerValor()).thenReturn(1);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(0);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacanteMock);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        unaBatalla.batallar();

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());

    }

}
