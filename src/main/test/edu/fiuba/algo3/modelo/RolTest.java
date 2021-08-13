package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallas_de_dados.Dados;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;
import edu.fiuba.algo3.modelo.roles.RolIndefinido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    @Test
    public void siLaCantidadEsMayorATresQuedaComo3EnAtacante() {
        Atacante atacante = new Atacante(5);
        Dados dados = atacante.tirarDados();
        assertEquals(3, dados.obtenerCantidad());
    }

    @Test
    public void siLaCantidadEsMayorATresQuedaComo3EnDefensor() {
        Defensor defensor = new Defensor(5);
        Dados dados = defensor.tirarDados();
        assertEquals(3, dados.obtenerCantidad());
    }

    @Test
    public void siLaCantidadEsMenorATresQuedaConEsaCantidadAtacante() {
        Atacante atacante = new Atacante(2);
        Dados dados = atacante.tirarDados();
        assertEquals(2, dados.obtenerCantidad());
    }

    @Test
    public void siLaCantidadEsMenorATresQuedaConEsaCantidadDefensor(){
        Defensor defensor = new Defensor(2);
        Dados dados = defensor.tirarDados();
        assertEquals(2, dados.obtenerCantidad());
    }

    @Test
    public void elRolIndefinidoDaSiempreNullEnLosDados(){
        RolIndefinido indef = new RolIndefinido();
        Dados dados = indef.tirarDados();
        assertNull(dados);
    }
}
