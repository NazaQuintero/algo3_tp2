package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

public class DadoTest {
    @Test
    public void dadoLanzaUnValorAleatorioEntreUnoYSeis() {
        Dado unDado = new Dado();
        unDado.lanzar();

        assert(unDado.obtenerValor() >= 1 && unDado.obtenerValor() <= 6);
    }
}
