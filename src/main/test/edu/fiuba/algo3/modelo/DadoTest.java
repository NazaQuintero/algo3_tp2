package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dado;
import edu.fiuba.algo3.modelo.batallasDeDados.DadoPersonalizado;
import edu.fiuba.algo3.modelo.batallasDeDados.DadoRandom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DadoTest {
    @Test
    public void unDadoRandomDevuelveUnValorAleatorioEntreUnoYSeisAlLanzar() {
        Dado unDado = new DadoRandom();
        unDado.lanzar();

        assert(unDado.obtenerValor() >= 1 && unDado.obtenerValor() <= 6);
    }

    @Test
    public void unDadoPersonalizadoDevuelveElValorRecibidoAlLanzar() {
        Dado unDado = new DadoPersonalizado(3);
        unDado.lanzar();

        assertEquals(3, unDado.obtenerValor());
    }

    @Test
    public void unDadoEsMayorAOtroSoloSiSuValorEsMayorExtricto() {
        Dado dado1 = new DadoPersonalizado(3);
        Dado dado2 = new DadoPersonalizado(2);
        Dado dado3 = new DadoPersonalizado(3);

        assertTrue(dado1.esMayorQue(dado2));
        assertFalse(dado1.esMayorQue(dado3));
    }
}
