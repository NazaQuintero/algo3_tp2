package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallas_de_dados.DadoPersonalizado;
import edu.fiuba.algo3.modelo.batallas_de_dados.DadoRandom;
import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DadosTest {

    @Test
    public void porDefectoLaCantidadDeDadosEs3() {
        Dados dados = new Dados(3);
        assertEquals(3, dados.obtenerCantidad());
    }

    @Test
    public void alRemoverUnDadoLaCantidadEs2() {
        Dados dados = new Dados(3);
        dados.removerUnDado();
        assertEquals(2, dados.obtenerCantidad());
    }

    @Test
    public void alRemover3DadosYAgregar1LaCantidadEs1() {
        Dados dados = new Dados(3);
        dados.removerUnDado();
        dados.removerUnDado();
        dados.removerUnDado();
        dados.agregarDado(new DadoRandom());
        assertEquals(1, dados.obtenerCantidad());
    }

    @Test
    public void porDefectoLosValoresDeCadaDadoEstanOrdenados() {
        Dados dados = new Dados(3);
        assertTrue(dados.estaOrdenadaDescendente());
    }

    @Test
    public void losValoresDeTiradaEstanOrdenadosDescendenteLuegoDeOrdenarlos() {
        Dados dados = new Dados();
        dados.lanzar();
        dados.ordenarDescendente();

        assertTrue(dados.estaOrdenadaDescendente());

    }

    @Test
    public void luegoDeAgregarDadosYOrdenarLosValoresObtenidosSonLosCorrectos() {
        Dados dados = new Dados();
        dados.agregarDado(new DadoPersonalizado(2));
        dados.agregarDado(new DadoPersonalizado(4));
        dados.agregarDado(new DadoPersonalizado(1));

        assertFalse(dados.estaOrdenadaDescendente());

        dados.ordenarDescendente();
        assertTrue(dados.estaOrdenadaDescendente());

        assertEquals(4, dados.obtenerDado(0).obtenerValor());
        assertEquals(2, dados.obtenerDado(1).obtenerValor());
        assertEquals(1, dados.obtenerDado(2).obtenerValor());
    }


}
