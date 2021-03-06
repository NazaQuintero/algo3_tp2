package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExcepcionesTest {

    @Test
    public void alTratarDeCargarContinentesYPaisesNoSeLanzaNingunaExcepcion() {
        assertDoesNotThrow(CargarJuego::cargarContinentesYPaises);
    }

    @Test
    public void alTratarDeCargarPaisesLimitrofesNoSeLanzaNingunaExcepcion() {
        assertDoesNotThrow(CargarJuego::cargarPaisesLimitrofes);
    }

    @Test
    public void alTratarDeCargarTarjetasNoSeLanzaNingunaExcepcion() {
        assertDoesNotThrow(CargarJuego::cargarTarjetas);
    }

    @Test
    public void atacarDeAtaqueLanzaExcepcionDeEjercitosInsuficientesSiElPaisAtacanteNoTieneEjercitos() {
        Ataque ataque = new Ataque();
        Pais arg = new Pais("Argentina");
        Pais chl = new Pais("Chile");
        arg.limitaCon(chl);
        chl.limitaCon(arg);
        assertThrows(CantidadDeEjercitosInValidaException.class, () -> ataque.atacarA(arg, chl, 3));
    }

    @Test
    public void alReagruparLanzaExcepcionDeElPaisNoEsLimitrofeSiLosMismosNoLoSon() {
        Pais argentina = new Pais("Argentina");
        Pais mexico = new Pais("Mexico");
        assertThrows(ElPaisNoEsLimitrofeException.class, () -> argentina.reagrupar(mexico, 3));
    }

    @Test
    public void seLanzaJugadorNoPoseePaisDeLaTarjetaExceptionSiTrataDeActivarTarjetaDeUnPaisQueNoEstaBajoSuDominio() {
        Jugador jugador = new Jugador("Carlitos", Color.RED);
        Pais pais = new Pais("Peru");
        Tarjeta tarjeta = new Tarjeta(pais, new Globo());
        jugador.recibirTarjeta(tarjeta);
        assertThrows(JugadorNoPoseePaisDeLaTarjetaException.class, () -> jugador.activarTarjeta(tarjeta));
    }

    @Test
    public void seLanzaPaisOcupadoPorOtroJugadorExceptionSiElJugadorTrataDeColocarEjercitoEnUnPaisDeOtroJugador() {
        Jugador unJugador = new Jugador("Andrea", Color.RED);
        Pais unPais = new Pais("Mexico");
        unPais.colocarEjercito(new Ejercito(new Jugador("Roque", Color.BLUE)));
        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unJugador.colocarEjercitos(unPais, 3));
    }

    @Test
    public void jugadorNoExisteException(){
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(new Jugador("Martin", Color.RED));

        assertThrows(JugadorNoExisteException.class, () -> jugadores.obtenerJugador(5));
    }

    @Test
    public void colocarCantidadNoPermitidaDeEjercitos(){
        Jugador jugador = Mockito.mock(Jugador.class);
        Pais pais = new Pais("Argentina");
        Mockito.when(jugador.cantidadPaisesDominados()).thenReturn(10);
        Colocacion ronda = new Colocacion(jugador);

        assertDoesNotThrow(() -> ronda.colocarEjercitos(pais, 5));
        assertThrows(NoQuedanMasEjercitosPorColocarException.class, () -> ronda.colocarEjercitos(pais, 1));
    }
}

