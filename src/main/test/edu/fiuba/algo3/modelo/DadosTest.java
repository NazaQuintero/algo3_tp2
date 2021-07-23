package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.Dado;
import edu.fiuba.algo3.modelo.batallasDeDados.Dados;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        dados.agregarDado(new Dado());
        assertEquals(1, dados.obtenerCantidad());
    }

    /*@Test
    public void porDefectoElValorDeTiradaDeCadaDadoEs1() {
        Dados dados = new Dados();
        for(Dado dado: dados) assertEquals(1, dado.obtenerValor());
    }*/

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


}
