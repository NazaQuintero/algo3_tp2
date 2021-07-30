package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.*;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.roles.Atacante;
import edu.fiuba.algo3.modelo.roles.Defensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatallaTest {

    @Test
    public void batallarEntrePaisesGanaAtacanteYColocaUnEjercitoEnElPaisDerrotado() {

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        Jugador jAtacante = new Jugador( "Martin");
        Jugador jDefensor = new Jugador( "Naza");


        Dados dadosAtacante = new Dados();
        Dado dadoPersonalizadoAtacante = new DadoPersonalizado(6);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);

        Dados dadosDefensor = new Dados();
        Dado dadoPersonalizadoDefensor = new DadoPersonalizado(1);
        dadosDefensor.agregarDado(dadoPersonalizadoDefensor);
        dadosDefensor.agregarDado(dadoPersonalizadoDefensor);
        dadosDefensor.agregarDado(dadoPersonalizadoDefensor);

        paisAtacante.colocarEjercito(new Ejercito(jAtacante));
        paisDefensor.colocarEjercito(new Ejercito(jDefensor));
        paisAtacante.modificarCantidadEjercito(2);

        // Inyectamos los DadoPersonalizado(s) en los Ejercito(s)
        paisAtacante.setDados(dadosAtacante);
        paisDefensor.setDados(dadosDefensor);


        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(paisAtacante, paisDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jAtacante, paisDefensor.dominadoPor());

    }

    @Test
    public void batallarEntrePaisesDefensorGanaYElAtacanteQuedaConUnSoloEjercito() {
        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        Jugador jAtacante = new Jugador( "Martin");
        Jugador jDefensor = new Jugador( "Naza");


        Dados dadosAtacante = new Dados();
        Dado dadoPersonalizadoAtacante = new DadoPersonalizado(1);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);

        Dados dadosDefensor = new Dados();
        Dado dadoPersonalizadoDefensor = new DadoPersonalizado(6);
        dadosDefensor.agregarDado(dadoPersonalizadoDefensor);

        paisAtacante.colocarEjercito(new Ejercito(jAtacante));
        paisDefensor.colocarEjercito(new Ejercito(jDefensor));
        paisAtacante.modificarCantidadEjercito(1);
        paisDefensor.modificarCantidadEjercito(1);

        paisAtacante.setDados(dadosAtacante);
        paisDefensor.setDados(dadosDefensor);

        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(paisAtacante, paisDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(2, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jDefensor, paisDefensor.dominadoPor());

    }

    @Test
    public void batallarEntrePaisesHayEmpateYGanaDefensor(){
        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        Jugador jAtacante = new Jugador( "Martin");
        Jugador jDefensor = new Jugador( "Naza");

        Dados dadosAtacante = new Dados();
        Dado dadoPersonalizadoAtacante = new DadoPersonalizado(1);
        dadosAtacante.agregarDado(dadoPersonalizadoAtacante);

        Dados dadosDefensor = new Dados();
        Dado dadoPersonalizadoDefensor = new DadoPersonalizado(1);
        dadosDefensor.agregarDado(dadoPersonalizadoDefensor);

        paisAtacante.colocarEjercito(new Ejercito(jAtacante));
        paisDefensor.colocarEjercito(new Ejercito(jDefensor));
        paisAtacante.modificarCantidadEjercito(1);

        paisAtacante.setDados(dadosAtacante);
        paisDefensor.setDados(dadosDefensor);


        ResultadoBatalla resultadoBatalla = new ResultadoBatalla(paisAtacante, paisDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(1, paisAtacante.cantidadEjercitos());
        assertEquals(jDefensor, paisDefensor.dominadoPor());
        assertEquals(jAtacante, paisAtacante.dominadoPor());
    }

}