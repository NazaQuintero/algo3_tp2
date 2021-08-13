package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallas_de_dados.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoDeContinentesYPaisesNoEncontradoException;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.observables.Observer;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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


    @Test
    public void dosPaisesPorDefectoNoSonLimitrofes() {
        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        assertFalse(unPais.esLimitrofeCon(otroPais));

        unPais.limitaCon(otroPais);

        assertTrue(unPais.esLimitrofeCon(otroPais));

    }

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
        Jugador jAtacante = new Jugador( "Martin", Color.RED);
        Jugador jDefensor = new Jugador( "Naza"  , Color.RED);

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

    @Test
    public void reagrupeEntrePaises() {
        Ejercito e1 = new Ejercito(new Jugador("Martin", Color.RED));
        Ejercito e2 = new Ejercito(new Jugador("Martin", Color.BLUE));
        Pais pais1 = new Pais("Argentina");
        Pais pais2 = new Pais("Brasil");

        pais1.colocarEjercito(e1);
        pais2.colocarEjercito(e2);
        pais1.modificarCantidadEjercito(9);

        pais1.limitaCon(pais2);

        // pais1 va a quedar con 6 y pais2 con 5
        try {
            pais1.reagrupar(pais2, 4);
        } catch (Exception ignored) {
        }

        assertEquals(6, pais1.cantidadEjercitos());
        assertEquals(5, pais2.cantidadEjercitos());
    }

    @Test
    public void getLimitrofesFunciona(){
        Pais pais = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais uru = new Pais("Uruguay");

        pais.limitaCon(bra);
        pais.limitaCon(uru);
        ArrayList<Pais> limitrofes = pais.getPaisesLimitrofes();
        assertEquals(bra, limitrofes.get(0));
        assertEquals(uru, limitrofes.get(1));
    }

    @Test
    public void andanLosObserver(){
        Pais pais = new Pais("Argentina");

        class ObsPersonalizado implements Observer {
            private int cantidad = 1;

            @Override
            public void update() {
                cantidad += 1;
            }
        }

        ObsPersonalizado obs = new ObsPersonalizado();

        pais.addObserver(obs);
        assertEquals(1, obs.cantidad);
        pais.notifyObservers();
        assertEquals(2, obs.cantidad);
        pais.removeObserver(obs);
        pais.notifyObservers();
        assertEquals(2, obs.cantidad);

    }

    @Test
    public void getEjercitoDevuelveElEjercito(){
        Ejercito ej = new Ejercito(new Jugador("Martin", Color.RED));
        Pais pais = new Pais("Argentina");
        pais.colocarEjercito(ej);
        assertEquals(ej, pais.getEjercito());
    }

    @Test
    public void getPosX(){
        try{ CargarJuego.cargarContinentesYPaises(); }
        catch (ArchivoDeContinentesYPaisesNoEncontradoException ignored){ }
        assertEquals(320, MultitonPaises.obtenerInstanciaDe("Argentina").getPosX());
    }

    @Test
    public void getPosY(){
        try{ CargarJuego.cargarContinentesYPaises(); }
        catch (ArchivoDeContinentesYPaisesNoEncontradoException ignored){ }
        assertEquals(510, MultitonPaises.obtenerInstanciaDe("Argentina").getPosY());
    }
}
