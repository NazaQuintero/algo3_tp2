package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Reagrupe;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExcepcionesTest {

    @Test
    public void alTratarDeCargarContinentesLanzaExcepcionDeArchivoDeContinentesNoEncontrado(){
        assertThrows(ArchivoDeContinentesNoEncontradoException.class, () -> CargarJuego.cargarContinentes("tita"));
    }

    @Test
    public void alTratarDeCargarPaisesLanzaExcepcionDeArchivoDePaisesNoEncontrado(){
        assertThrows(ArchivoDePaisesNoEncontradoException.class, () -> CargarJuego.cargarPaisesLimitrofes("alfajor"));
    }

    @Test
    public void alTratarDeCargarTarjetasLanzaExcepcionDeArchivoDeTarjetasNoEncontrado(){
        assertThrows(ArchivoDeTarjetasNoEncontradoException.class, () -> CargarJuego.cargarTarjetas(new Juego(), "pochoclo"));
    }

    @Test
    public void atacarDeAtaqueLanzaExcepcionDeEjercitosInsuficientesSiElPaisAtacanteNoTieneEjercitos() {
        Ataque ataque = new Ataque();
        assertThrows(EjercitosInsuficientesException.class, () -> ataque.atacarA(new Pais("Argentina"),new Pais("Chile"), 3 ));
    }

    @Test
    public void alReagruparLanzaExcepcionDeElPaisNoEsLimitrofeSiLosMismosNoLoSon() {
        Pais argentina = new Pais("Argentina");
        Pais mexico = new Pais("Mexico");
        assertThrows(ElPaisNoEsLimitrofeException.class, () -> argentina.reagrupar(mexico, 3));
    }

    @Test
    public void alTratarDeObtenerUnJugadorPorNombreDeJugadoresSeLanzaJugadorNoExisteExceptionDeNoExistir() {
        Jugadores jugadores = new Jugadores();
        assertThrows(JugadorNoExisteException.class, () -> jugadores.obtenerJugador("Nestor"));
    }

    @Test
    public void seLanzaJugadorNoPoseePaisDeLaTarjetaExceptionSiTrataDeActivarTarjetaDeUnPaisQueNoEstaBajoSuDominio() {
        Jugador jugador = new Jugador("Carlitos", Color.RED);
        assertThrows(JugadorNoPoseePaisDeLaTarjetaException.class, () -> jugador.activarTarjetaPais(new Pais("Peru")));
    }

    @Test
    public void seLanzaPaisOcupadoPorOtroJugadorExceptionSiElJugadorTrataDeColocarEjercitoEnUnPaisDeOtroJugador() {
        Jugador unJugador = new Jugador("Andrea", Color.RED);
        Pais unPais = new Pais("Mexico");
        unPais.colocarEjercito(new Ejercito(new Jugador("Roque", Color.BLUE)));
        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unJugador.colocarEjercitos(unPais, 3));
    }
}

