package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.DadoRandom;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;
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
        ejercitoAtacante.asignarRol(new Atacante());
        ejercitoAtacante.modificarCantidad(4);

        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);
        ejercitoDefensor.asignarRol(new Defensor());
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);


        DadoRandom dadoAtacanteMock = mock(DadoRandom.class);
        DadoRandom dadoDefensorMock = mock(DadoRandom.class);

        when(dadoAtacanteMock.esMayorQue(dadoDefensorMock)).thenReturn(true);

        when(dadoAtacanteMock.obtenerPais()).thenReturn(paisAtacante);
        when(dadoDefensorMock.obtenerPais()).thenReturn(paisDefensor);

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

        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(dadosAtacante, dadosDefensor);
//        resultadoBatalla.procesarResultado();
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

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
        ejercitoAtacante.asignarRol(new Atacante());
        ejercitoAtacante.modificarCantidad(4);

        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);
        ejercitoDefensor.asignarRol(new Defensor());
        ejercitoDefensor.modificarCantidad(2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        DadoRandom dadoAtacanteMock = mock(DadoRandom.class);
        when(dadoAtacanteMock.obtenerValor()).thenReturn(1);
        when(dadoAtacanteMock.obtenerPais()).thenReturn(paisAtacante);

        DadoRandom dadoDefensorMock = mock(DadoRandom.class);
        when(dadoDefensorMock.obtenerValor()).thenReturn(6);
        when(dadoDefensorMock.obtenerPais()).thenReturn(paisDefensor);
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

        dadosAtacante.asignarPais(paisAtacante);
        dadosDefensor.asignarPais(paisDefensor);

        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(dadosAtacante, dadosDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

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
        ejercitoAtacante.asignarRol(new Atacante());
        ejercitoAtacante.modificarCantidad(1);

        Ejercito ejercitoDefensor = new Ejercito(jugadorDefensorMock);
        ejercitoDefensor.asignarRol(new Defensor());

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisDefensor.colocarEjercito(ejercitoDefensor);

        DadoRandom dadoAtacanteMock = mock(DadoRandom.class);
        when(dadoAtacanteMock.obtenerValor()).thenReturn(1);
        when(dadoAtacanteMock.obtenerPais()).thenReturn(paisAtacante);

        DadoRandom dadoDefensorMock = mock(DadoRandom.class);
        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(0);
        when(dadoDefensorMock.obtenerPais()).thenReturn(paisDefensor);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacanteMock);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensorMock);

        when(jugadorAtacanteMock.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugadorDefensorMock.tirarDados(paisDefensor)).thenReturn(dadosDefensor);

        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(dadosAtacante, dadosDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jugadorDefensorMock, paisDefensor.dominadoPor());

    }

}
