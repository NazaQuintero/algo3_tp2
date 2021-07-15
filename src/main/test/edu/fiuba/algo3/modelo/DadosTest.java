package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class DadosTest {
    @Test
    public void ElDadoLanza1ValorAleatorioEntre1Y6() {
        Dados unDado = new Dados(1); // se va a lanzar en total 1 dado
        LanzadaDeDados valores = unDado.lanzar();

        assert(valores.verValor(0) >= 1 && valores.verValor(0) <= 6);
    }

    @Test
    public void ElDadoLanza2ValoresAleatoriosEntre1Y6() {
        Dados unDado = new Dados(2); // se van a lanzar en total 2 dado
        LanzadaDeDados valores = unDado.lanzar();

        assert(valores.verValor(0) >= 1 && valores.verValor(0) <= 6);
        assert(valores.verValor(1) >= 1 && valores.verValor(1) <= 6);
    }

    @Test
    public void ElDadoLanza3ValoresAleatoriosEntre1Y6() {
        Dados unDado = new Dados(3); // se van a lanzar en total 3 dado
        LanzadaDeDados valores = unDado.lanzar();

        assert(valores.verValor(0) >= 1 && valores.verValor(0) <= 6);
        assert(valores.verValor(1) >= 1 && valores.verValor(1) <= 6);
        assert(valores.verValor(2) >= 1 && valores.verValor(2) <= 6);
    }
}
