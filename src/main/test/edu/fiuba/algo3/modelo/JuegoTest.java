package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.MultitonTarjetas;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;
import edu.fiuba.algo3.modelo.tarjetas.Globo;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuegoTest {

    @Test
    public void elJuegoNoComienzaSinUnMinimoDe2Jugadores() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ArchivoDeContinentesNoEncontradoException {

        Juego juego = new Juego();
        Jugador jugador = new Jugador("Juani", Color.AQUA);
        juego.agregarJugador(jugador);
        assertThrows(CantidadDeJugadoresInsuficienteException.class, juego::comenzar);
    }

    @Test
    public void seRepartenEquitativamente25PaisesEntreDosJugadores() throws CantidadDeJugadoresInsuficienteException, ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, ArchivoDeContinentesNoEncontradoException {
        Juego juego = new Juego();
        Jugador jugador1 = new Jugador("Martin", Color.BLUE);
        Jugador jugador2 = new Jugador("Juani", Color.RED);

        juego.agregarJugador(jugador1);
        juego.agregarJugador(jugador2);
        juego.comenzar();

        assertEquals(25,jugador1.cantidadPaisesDominados());
        assertEquals(25,jugador2.cantidadPaisesDominados());
    }

    @Test
    public void canjeFunciona() throws ArchivoDeTarjetasNoEncontradoException, ArchivoDePaisesNoEncontradoException, JugadorSinTarjetasException, SinCanjeHabilitadoException, ArchivoDeContinentesNoEncontradoException, CantidadDeJugadoresInsuficienteException {
        Juego juego = new Juego();
        Pais arg = MultitonPaises.obtenerInstanciaDe("Argentina");
        Pais bra = MultitonPaises.obtenerInstanciaDe("Brasil");
        Pais chl = MultitonPaises.obtenerInstanciaDe("Chile");

        Tarjeta unaTarjeta = new Tarjeta(arg, new Globo());
        Tarjeta otraTarjeta = new Tarjeta(bra, new Globo());
        Tarjeta ootraTarjeta = new Tarjeta(chl, new Globo());

        juego.agregarTarjeta(unaTarjeta);
        juego.agregarTarjeta(otraTarjeta);
        juego.agregarTarjeta(ootraTarjeta);

        Jugador jugador = new Jugador("Cami", Color.RED);
        Jugador otroJugador = new Jugador("Frank", Color.PINK);
        juego.agregarJugador(jugador);
        juego.agregarJugador(otroJugador);

        juego.comenzar();
        Jugador jugadorQueLeToca = juego.getTurno().obtenerJugadorTurnoActual();

        arg.colocarEjercito(new Ejercito(jugador));
        bra.colocarEjercito(new Ejercito(jugador));
        chl.colocarEjercito(new Ejercito(jugador));

        Tarjeta tArg = MultitonTarjetas.obtenerTarjeta(arg);
        Tarjeta tBra = MultitonTarjetas.obtenerTarjeta(bra);
        Tarjeta tChl = MultitonTarjetas.obtenerTarjeta(chl);

        jugadorQueLeToca.recibirTarjeta(tArg);
        jugadorQueLeToca.recibirTarjeta(tBra);
        jugadorQueLeToca.recibirTarjeta(tChl);

        ArrayList<Tarjeta> paisesTarjetas = new ArrayList<>();
        paisesTarjetas.add(tArg);
        paisesTarjetas.add(tBra);
        paisesTarjetas.add(tChl);
        jugadorQueLeToca.canjearTarjetas(paisesTarjetas);

        assertEquals(4, jugadorQueLeToca.obtenerCanjeActual().cantidadEjercitos());
    }

}
