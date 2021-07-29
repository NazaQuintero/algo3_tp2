package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.*;
import edu.fiuba.algo3.modelo.continentes.Asia;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.rondas.Ataque;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import edu.fiuba.algo3.modelo.rondas.Reagrupe;
import edu.fiuba.algo3.modelo.rondas.Ronda;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RondaTest {
    @Test
    public void unJugadorNoPuedeAtacarSiNoTieneTurno() throws Exception {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Juani");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        jugador2.colocarEjercitos(bra, 2);

        Turno turno = new ConTurno(jugadores);
        // Se selecciona el jugador1
        turno.seleccionarPrimerJugador(0);

        assertThrows(ElJugadorNoTieneTurnoException.class, () -> jugador2.atacarA(bra, arg, 1));

    }

    @Test
    public void elJugadorConTurnoNoPuedeAtacarSiLaRondaNoEsDeAtaque() throws Exception{
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais arg = new Pais("Argentina");
        Pais jpn = new Pais("Japon");

        jugador1.colocarEjercitos(arg, 3);
        jugador2.colocarEjercitos(jpn, 2);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        assertThrows(NoEsRondaDeAtaqueException.class, () -> jugador1.atacarA(arg, jpn, 1));
    }


    @Test
    public void elJugadorConTurnoNoPuedeReagruparSiLaRondaNoEsDeReagrupe() throws Exception{
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        jugadores.agregarJugador(jugador1);

        Pais arg = new Pais("Argentina");
        Pais jpn = new Pais("Japon");

        jugador1.colocarEjercitos(arg, 7);
        jugador1.colocarEjercitos(jpn, 1);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        assertThrows(NoEsRondaDeReagrupeException.class, () -> jugador1.reagrupar(arg, jpn, 3));
    }

    @Test
    public void elJugadorConTurnoNoPuedeColocarEjercitosSiLaRondaNoEsDeColocacion() throws Exception{
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        jugadores.agregarJugador(jugador1);

        Pais arg = new Pais("Argentina");
        Pais jpn = new Pais("Japon");

        jugador1.colocarEjercitos(arg, 7);
        jugador1.colocarEjercitos(jpn, 1);

        Turno turno = new ConTurno(jugadores);
        turno.setRonda(new Ataque());
        turno.seleccionarPrimerJugador(0);
        assertThrows(NoEsRondaDeColocacionException.class, () -> jugador1.colocarEjercitos(arg, 3));
    }


    @Test
    public void rondaConDosJugadoresNoSeAtacanYSeColocan2NuevosEjercitosCadaUno() throws Exception {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais arg = new Pais("Argentina");
        Pais jpn = new Pais("Japon");

        jugador1.colocarEjercitos(arg, 1);
        jugador2.colocarEjercitos(jpn, 1);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        // Le toca la colocacion al jugador1
        jugador1.colocarEjercitos(arg, 2);
        jugador1.finalizarRonda();

        jugador2.colocarEjercitos(jpn, 2);
        jugador2.finalizarRonda();

        assertEquals(3, arg.cantidadEjercitos());
        assertEquals(3, jpn.cantidadEjercitos());
    }

    @Test
    public void seActivaUnaTarjetaDePaisEnRondaDeColocacionSumandole2EjercitosADichoPais() throws Exception {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        jugadores.agregarJugador(jugador1);

        Pais arg = new Pais("Argentina");

        jugador1.colocarEjercitos(arg, 1);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        turno.setRonda(new Colocacion());

        Tarjeta tarjeta = new Tarjeta(arg, new Globo());
        jugador1.recibirTarjeta(tarjeta);

        // Al activar la tarjeta se le suman 2 ejercitos
        jugador1.activarTarjetaPais(arg);

        assertEquals(3, arg.cantidadEjercitos());
    }

    @Test
    public void noSePuedeActivarUnaTarjetaDePaisEnUnaRondaDeAtaque() throws Exception{
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        jugadores.agregarJugador(jugador1);

        Pais arg = new Pais("Argentina");

        jugador1.colocarEjercitos(arg, 1);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        turno.setRonda(new Ataque());

        Tarjeta tarjeta = new Tarjeta(arg, new Globo());
        jugador1.recibirTarjeta(tarjeta);

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> jugador1.activarTarjetaPais(arg));
    }

    @Test
    public void noSePuedeActivarUnaTarjetaDePaisEnUnaRondaDeReagrupe() throws Exception{
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        jugadores.agregarJugador(jugador1);

        Pais arg = new Pais("Argentina");

        jugador1.colocarEjercitos(arg, 1);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);

        turno.setRonda(new Reagrupe());

        Tarjeta tarjeta = new Tarjeta(arg, new Globo());
        jugador1.recibirTarjeta(tarjeta);

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> jugador1.activarTarjetaPais(arg));

    }

    @Test
    public void seJuegaUnaRondaCon3JugadoresJugador2DominaAsiaNadieAtacaPeroSiColocan() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, JugadorNoExisteException {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");
        Jugador jugador3 = new Jugador(1, "Juani");

        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais jpn = new Pais("Japon");
        Pais rsa = new Pais("Rusia");

        Asia asia = new Asia();
        asia.agregarPais(jpn);
        asia.agregarPais(rsa);

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0);
        turno.setRonda(new Colocacion());

        jugador1.colocarEjercitos(arg, 1);
        jugador1.finalizarRonda();

        jugador2.colocarEjercitos(jpn, 1);
        jugador2.colocarEjercitos(rsa, 1);
        jugador2.finalizarRonda();

        jugador3.colocarEjercitos(bra, 1);
        jugador3.finalizarRonda();


        // Asia dominado por Jugador2
        assertTrue(asia.dominadoPor(jugador2));
    }


    @Test
    public void rondaDeAtaqueEntre2JugadoresJugador1AtacaYConquista2PaisesDelJugador2() throws Exception {
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(0, "Martin");
        Jugador jugador2 = new Jugador(1, "Naza");

        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais jpn = new Pais("Japon");

        arg.limitaCon(bra);
        arg.limitaCon(jpn);

        Dado dadoAtacante = new DadoPersonalizado(6);
        Dado dadoDefensor = new DadoPersonalizado(1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);

        Dados dadosDefensor = new Dados();
        dadosDefensor.agregarDado(dadoDefensor);

        arg.colocarEjercito(new Ejercito(jugador1));
        arg.modificarCantidadEjercito(6);
        bra.colocarEjercito(new Ejercito(jugador2));
        jpn.colocarEjercito(new Ejercito(jugador2));

        arg.ejercito.setDados(dadosAtacante);
        bra.ejercito.setDados(dadosDefensor);
        jpn.ejercito.setDados(dadosDefensor);

        Turno unTurno = new ConTurno(jugadores);
        unTurno.setRonda(new Ataque());
        unTurno.seleccionarPrimerJugador(0);

        //Ataque al primer pais del jugador2
        Resultado resultadoBatalla = jugador1.atacarA(arg, bra, 2);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);
        assertEquals(jugador1, bra.dominadoPor());

        //Ataque al segundo pais del jugador2
        resultadoBatalla = jugador1.atacarA(arg, jpn, 4);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);
        assertEquals(jugador1, jpn.dominadoPor());
    }

}


