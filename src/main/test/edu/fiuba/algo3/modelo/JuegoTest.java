package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ArchivoDeContinentesNoEncontradoException {

        Juego juego = new Juego();
        juego.agregarJugador("Juani");
        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }

    @Test
    public void seRepartenEquitativamente25PaisesEntreDosJugadores() throws CantidadDeJugadoresInsuficienteException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ArchivoDeContinentesNoEncontradoException {
        Juego juego = new Juego();
        Jugador jugador1 = new Jugador("Martin");
        Jugador jugador2 = new Jugador("Juani");

        juego.agregarJugador(jugador1);
        juego.agregarJugador(jugador2);
        juego.comenzar();

        assertEquals(25,juego.cantidadPaisesDominados(jugador1));
        assertEquals(25,juego.cantidadPaisesDominados(jugador2));
    }

    @Test
    public void canjeFunciona() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ElJugadorNoTieneTurnoException, JugadorNoExisteException, ActivacionTarjetaEnRondaEquivocadaException, LaTarjetaYaFueActivadaException, TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException, ArchivoDeContinentesNoEncontradoException {
        Juego juego = new Juego();
        Pais arg = juego.obtenerPais("Argentina");
        Pais bra = juego.obtenerPais("Brasil");
        Pais chl = juego.obtenerPais("Chile");

        Tarjeta unaTarjeta = new Tarjeta(arg, new Globo());
        Tarjeta otraTarjeta = new Tarjeta(bra, new Globo());
        Tarjeta ootraTarjeta = new Tarjeta(chl, new Globo());

//        juego.agregarPais(arg);
//        juego.agregarPais(bra);
//        juego.agregarPais(chl);

        juego.agregarTarjeta(unaTarjeta);
        juego.agregarTarjeta(otraTarjeta);
        juego.agregarTarjeta(ootraTarjeta);

        Jugador jugador = new Jugador("Cami");

        juego.agregarJugador(jugador);
        juego.agregarJugador("Frank");

        //juego.comenzar();
        juego.iniciarTurno();

        arg.colocarEjercito(new Ejercito(jugador));
        bra.colocarEjercito(new Ejercito(jugador));
        chl.colocarEjercito(new Ejercito(jugador));
//        juego.colocarEjercitos(jugador, arg, 1);
//        juego.colocarEjercitos(jugador, bra, 1);
//        juego.colocarEjercitos(jugador, chl, 1);

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
