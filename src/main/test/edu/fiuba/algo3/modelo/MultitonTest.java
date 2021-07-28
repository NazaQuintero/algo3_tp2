package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultitonTest {

    @Test
    public void alPedir2VecesElMismoContinenteLaInstanciaObtenidaDeContinenteEsLaMisma() {
        Continente continente1 = MultitonContinentes.obtenerInstanciaDe("Europa");
        Continente continente2 = MultitonContinentes.obtenerInstanciaDe("Europa");

        assertEquals(continente1, continente2);
    }

    @Test
    public void alPedirUnContinenteNoConocidoDevuelveAsia() {
        Continente continente1 = MultitonContinentes.obtenerInstanciaDe("Carlitos");

        assertEquals("Asia",continente1.obtenerNombre());
    }

    @Test
    public void alPedirUnContinenteDevuelveCorrectamente() {
        Continente continente1 = MultitonContinentes.obtenerInstanciaDe("Oceania");

        assertEquals("Oceania",continente1.obtenerNombre());
    }
}
