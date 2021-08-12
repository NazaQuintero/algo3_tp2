package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeContinentesYPaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.excepciones.ContinenteInvalidoException;
import edu.fiuba.algo3.modelo.continentes.MultitonContinentes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultitonTest {

    private static final String ARCHIVO_CONTINENTES = "continentesYPaises.json";

    @Test
    public void laCantidadDeContinentesEsCeroLuegoDeReiniciar() {
        MultitonContinentes.reiniciar();
        assertEquals(0, MultitonContinentes.cantidadDeContinentes());
    }

    @Test
    public void alPedir2VecesElMismoContinenteLaInstanciaObtenidaDeContinenteEsLaMisma() throws ContinenteInvalidoException, ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises();
        Continente continente1 = MultitonContinentes.obtenerInstanciaDe("Africa");
        Continente continente2 = MultitonContinentes.obtenerInstanciaDe("Africa");

        assertEquals(continente1, continente2);
    }

    @Test
    public void alPedirUnContinenteNoConocidoDevuelveAsia()  {

        assertThrows(ContinenteInvalidoException.class, () -> MultitonContinentes.obtenerInstanciaDe("Carlitos"));
    }

    @Test
    public void alPedirUnContinenteDevuelveCorrectamente() throws ContinenteInvalidoException, ArchivoDeContinentesYPaisesNoEncontradoException {
        CargarJuego.cargarContinentesYPaises();
        Continente continente1 = MultitonContinentes.obtenerInstanciaDe("Oceania");

        assertEquals("Oceania",continente1.getNombre());
    }
}
