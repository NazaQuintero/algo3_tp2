package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continenta;
import edu.fiuba.algo3.modelo.continentes.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultitonTest {

    @Test
    public void alPedir2VecesElMismoContinenteLaInstanciaObtenidaDeContinenteEsLaMisma() throws ContinenteInvalidoException {
        CargarJuego.cargarContinentes();
        Continenta continente1 = MultitonContinentes.obtenerInstanciaDe("Africa");
        Continenta continente2 = MultitonContinentes.obtenerInstanciaDe("Africa");

        assertEquals(continente1, continente2);
    }

    @Test
    public void alPedirUnContinenteNoConocidoDevuelveAsia()  {

        assertThrows(ContinenteInvalidoException.class, () -> MultitonContinentes.obtenerInstanciaDe("Carlitos"));
    }

    @Test
    public void alPedirUnContinenteDevuelveCorrectamente() throws ContinenteInvalidoException {
        CargarJuego.cargarContinentes();
        Continenta continente1 = MultitonContinentes.obtenerInstanciaDe("Oceania");

        assertEquals("Oceania",continente1.getNombre());
    }
}
