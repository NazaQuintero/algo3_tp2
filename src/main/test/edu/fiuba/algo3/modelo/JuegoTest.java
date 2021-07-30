package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException {

        Juego juego = new Juego();
        juego.agregarJugador("Juani");
        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }


    @Test
    public void seColocan1EjercitosEnElMismoPais() throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException, JugadorNoExisteException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, PaisOcupadoPorOtroJugadorException {

        Juego juego = new Juego();

        Jugador jugador1 = juego.agregarJugador("Juani");
        Pais pais = juego.obtenerPais("Argentina");

        juego.colocarEjercitos(jugador1, pais, 1);

        assertEquals(1,juego.cantidadEjercitosEn(pais));
        assertEquals(jugador1, juego.paisDominadoPor(pais));

    }


    @Test
    public void seRepartenEquitativamente25PaisesEntreDosJugadores() throws CantidadDeJugadoresInsuficienteException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException {
        Juego juego = new Juego();
        Jugador jugador1 = juego.agregarJugador("Martin");
        Jugador jugador2 = juego.agregarJugador("Juani");
        juego.comenzar();

        assertEquals(25,juego.cantidadPaisesDominados(jugador1));
        assertEquals(25,juego.cantidadPaisesDominados(jugador2));
    }

    @Test
    public void canjeFunciona() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, JugadorNoExisteException, ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException, TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException, PaisOcupadoPorOtroJugadorException {
        Juego juego = new Juego();
        Jugador jugador = juego.agregarJugador("Martin");
        juego.agregarJugador("Naza");
        //juego.comenzar();
        juego.setTurno();

        Pais arg = juego.obtenerPais("Argentina");
        Pais bra = juego.obtenerPais("Brasil");
        Pais chl = juego.obtenerPais("Chile");

        juego.colocarEjercitos(jugador, arg, 1);
        juego.colocarEjercitos(jugador, bra, 1);
        juego.colocarEjercitos(jugador, chl, 1);

        juego.recibirTarjeta(jugador, arg);
        juego.recibirTarjeta(jugador, bra);
        juego.recibirTarjeta(jugador, chl);

        juego.activarTarjeta(jugador, arg);
        juego.activarTarjeta(jugador, bra);
        juego.activarTarjeta(jugador, chl);

        ArrayList<Pais> paisesTarjetas = new ArrayList<>();
        paisesTarjetas.add(arg);
        paisesTarjetas.add(bra);
        paisesTarjetas.add(chl);
        juego.canjearTarjetas(jugador, paisesTarjetas);

        assertEquals(4, jugador.obtenerCanjeActual().cantidadEjercitos());
    }

}
