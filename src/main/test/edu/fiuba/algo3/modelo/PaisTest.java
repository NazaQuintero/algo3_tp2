package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.Pais;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    @Test
    public void porDefectoUnPaisNoEstaDominado() {

        Pais unPais = new Pais("Argentina");

        assertNull(unPais.dominadoPor());
    }

    @Test
    public void porDefectoUnPaisNoTieneEjercitos() {

        Pais unPais = new Pais("Argentina");
        assertEquals(0, unPais.cantidadEjercitos());
    }

    @Test
    public void laCantidadDeEjercitosColocadaEsCorrecta() {

        Pais unPais = new Pais("Argentina");
        Ejercito ejercito = new Ejercito(new Jugador( "Juani", Color.RED));
        ejercito.modificarCantidad(2);
        unPais.colocarEjercito(ejercito);
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void alColocarUnEjercitoEstaDominadoPorUnJugador() {

        Jugador unJugador = new Jugador( "Naza", Color.RED);
        Pais unPais = new Pais("Argentina");
        Ejercito unEjercito = new Ejercito(unJugador);
        unPais.colocarEjercito(new Ejercito(unJugador));

        assertEquals(unJugador, unPais.dominadoPor());
    }

    @Test
    public void unPaisQueTenia1EjercitoPasaATener4SiElMismoJugadorVuelveAColocar() {

        Jugador unJugador = new Jugador("Naza", Color.BLUE);
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(unJugador));

        assertEquals(1, unPais.cantidadEjercitos());

        unPais.modificarCantidadEjercito(3);

        assertEquals(4, unPais.cantidadEjercitos());
    }

    /*@Test
    public void noSePuedeColocarEjercitoEnUnPaisQueEstaDominadoPorOtroJugador() throws PaisOcupadoPorOtroJugadorException {

        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercito(new Ejercito(1, unJugador));


        assertThrows(PaisOcupadoPorOtroJugadorException.class, () -> unPais.colocarEjercito(3, otroJugador));
    }
    */


    @Test
    public void dosPaisesPorDefectoNoSonLimitrofes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        assertFalse(unPais.esLimitrofeCon(otroPais));

        unPais.limitaCon(otroPais); // aca establecemos que son limitrofes

        assertTrue(unPais.esLimitrofeCon(otroPais));

    }

    /*@Test
    public void ataqueEntrePaisesGanaDefensor() throws PaisOcupadoPorOtroJugadorException {

        Pais paisAtacante = new Pais("Argentina");
        paisAtacante.colocarEjercito(new Ejercito(new Jugador(), 3));

        Pais paisDefensor = new Pais("Brasil");
        paisDefensor.colocarEjercito(new Ejercito(new Jugador(), 3));

        //ArrayList<Integer> dadosAtacante = new ArrayList<>(Arrays.asList(2, 2, 2));

        paisAtacante.atacarA(paisDefensor, new Dados(2,2,2));

        assertEquals(0, paisAtacante.cantidadEjercitos());

    }*/


    @Test
    public void ataqueEntrePaisesGanaAtacanteYColocaUnEjercitoEnElPaisDerrotado() throws Exception {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        paisAtacante.limitaCon(paisDefensor);
        Jugador jAtacante = new Jugador( "Martin", Color.RED);
        Jugador jDefensor = new Jugador( "Naza", Color.RED);

        // Asigna que el Pais pertenezca a un jugador
        jAtacante.colocarEjercitos(paisAtacante, 4);
        jDefensor.colocarEjercitos(paisDefensor, 3);


        Dado dadoAtacante = new DadoPersonalizado(2);
        Dado dadoDefensor = new DadoPersonalizado(1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);

        paisAtacante.setDados(dadosAtacante);
        paisDefensor.setDados(dadosDefensor);

        Resultado resultadoBatalla = paisAtacante.atacarA(paisDefensor, 3);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jAtacante, paisDefensor.dominadoPor());
    }

    @Test
    public void ataqueEntrePaisesGanaDefensor() throws Exception {
        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        paisAtacante.limitaCon(paisDefensor);
        Jugador jAtacante = new Jugador( "Martin");
        Jugador jDefensor = new Jugador( "Naza");

        paisAtacante.colocarEjercito(new Ejercito(jAtacante));
        paisAtacante.modificarCantidadEjercito(3);
        paisDefensor.colocarEjercito(new Ejercito(jDefensor));
        paisDefensor.modificarCantidadEjercito(2);

        Dado dadoAtacante = new DadoPersonalizado(1);
        Dado dadoDefensor = new DadoPersonalizado(6);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);

        paisAtacante.setDados(dadosAtacante);
        paisDefensor.setDados(dadosDefensor);

        Resultado resultadoBatalla = paisAtacante.atacarA(paisDefensor, 3);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(3, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
    }
}
