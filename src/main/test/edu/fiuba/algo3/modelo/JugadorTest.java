package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.canjes.PrimerCanje;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import edu.fiuba.algo3.modelo.tarjetas.*;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {



    @Test
    public void seCreaUnJugadorConColorRosa() {

        Jugador jugador = new Jugador( "Cami");
        jugador.asignarColor("rosa");

        assertEquals(jugador.mostrarColor(), "rosa");

    }

    @Test
    public void porDefectoUnJugadorNoTieneColorAsignado() {

        Jugador jugador = new Jugador( "Cami");

        assertEquals(jugador.mostrarColor(), "");
    }

    @Test
    public void porDefectoUnJugadorNoTienePaises() {

        Jugador jugador = new Jugador( "Cami");

        assertEquals(jugador.cantidadPaisesDominados(), 0);
    }


    @Test
    public void luegoDeQueElJugadorActivaUnaTarjetaEstaQuedaActivadaYNoSePuedeVolverAActivar() throws Exception{
        Jugador unJugador = new Jugador( "Cami");
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(unJugador);
        Turno unTurno = new ConTurno(jugadores);

        unTurno.setRonda(new Colocacion());
        unJugador.setTurno(unTurno);

        Pais unPais = new Pais("Bolivia");
        unJugador.colocarEjercitos(unPais, 2);
        Simbolo unChirimbolito = new Globo();
        Tarjeta unaTarjeta = new Tarjeta(unPais, unChirimbolito);
        unJugador.recibirTarjeta(unaTarjeta);
        unJugador.activarTarjetaPais(unPais); // no hace falta testear q se hayan sumado los ejercitos xq eso ya lo probamos y funca

        assertThrows(LaTarjetaYaFueActivadaException.class, () -> unJugador.activarTarjetaPais(unPais));
    }

    @Test
    public void luegoDeCanjearTarjetasElJugadorYaNoLasPosee() throws Exception {
        Jugador jugador = new Jugador( "Martin");

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais col = new Pais("Colombia");

        Tarjeta tArg = new Tarjeta(arg, new Globo());
        Tarjeta tBra = new Tarjeta(bra, new Globo());
        Tarjeta tCol = new Tarjeta(col, new Globo());

        jugador.recibirTarjeta(tArg);
        jugador.recibirTarjeta(tBra);
        jugador.recibirTarjeta(tCol);

        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(tArg);
        tarjetas.add(tBra);
        tarjetas.add(tCol);

        tArg.activar();
        tBra.activar();
        tCol.activar();

        jugador.solicitarCanje(tarjetas);
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.buscarTarjeta(arg));
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.buscarTarjeta(bra));
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.buscarTarjeta(col));
    }

    @Test
    public void unJugadorQueNoRealizoCanjesRecibeUnPrimerCanjeAlSolicitarUnoYPuedeColocar4EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        Jugador jugador = new Jugador( "Martin");

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais col = new Pais("Colombia");

        Tarjeta tArg = new Tarjeta(arg, new Globo());
        Tarjeta tBra = new Tarjeta(bra, new Globo());
        Tarjeta tCol = new Tarjeta(col, new Globo());

        tArg.activar();
        tBra.activar();
        tCol.activar();

        jugador.recibirTarjeta(tArg);
        jugador.recibirTarjeta(tBra);
        jugador.recibirTarjeta(tCol);

        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(tArg);
        tarjetas.add(tBra);
        tarjetas.add(tCol);

        jugador.solicitarCanje(tarjetas);
        assertEquals(4, jugador.obtenerCanjeActual().cantidadEjercitos());
    }

    @Test
    public void unJugadorQueRealizoUnCanjeRecibeUnSegundoCanjeAlSolicitarOtroYPuedeColocar7EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        Jugador jugador = new Jugador( "Martin");

        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais col = new Pais("Colombia");

        Tarjeta tArg = new Tarjeta(arg, new Globo());
        Tarjeta tBra = new Tarjeta(bra, new Globo());
        Tarjeta tCol = new Tarjeta(col, new Globo());

        jugador.recibirTarjeta(tArg);
        jugador.recibirTarjeta(tBra);
        jugador.recibirTarjeta(tCol);

        ArrayList<Tarjeta> tarjetas = new ArrayList<>();
        tarjetas.add(tArg);
        tarjetas.add(tBra);
        tarjetas.add(tCol);

        tArg.activar();
        tBra.activar();
        tCol.activar();

        jugador.solicitarCanje(tarjetas);

        tarjetas.clear();

        arg = new Pais("Argentina2");
        bra = new Pais("Brasil2");
        col = new Pais("Colombia2");

        tArg = new Tarjeta(arg, new Globo());
        tBra = new Tarjeta(bra, new Globo());
        tCol = new Tarjeta(col, new Globo());

        jugador.recibirTarjeta(tArg);
        jugador.recibirTarjeta(tBra);
        jugador.recibirTarjeta(tCol);

        tarjetas.add(tArg);
        tarjetas.add(tBra);
        tarjetas.add(tCol);

        tArg.activar();
        tBra.activar();
        tCol.activar();

        jugador.solicitarCanje(tarjetas);

        assertEquals(7, jugador.obtenerCanjeActual().cantidadEjercitos());

    }


}
