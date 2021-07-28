package edu.fiuba.algo3.modelo;

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

        Jugador jugador = new Jugador();
        jugador.asignarColor("rosa");

        assertEquals(jugador.mostrarColor(), "rosa");

    }

    @Test
    public void porDefectoUnJugadorNoTieneColorAsignado() {

        Jugador jugador = new Jugador();

        assertEquals(jugador.mostrarColor(), "");
    }

    @Test
    public void porDefectoUnJugadorNoTienePaises() {

        Jugador jugador = new Jugador();

        assertEquals(jugador.cantidadPaisesDominados(), 0);
    }

    @Test
    public void porDefectoUnjugadorNoTieneObjetivoSecreto() {
        Jugador jugador = new Jugador();

        assertNull(jugador.obtenerObjetivoSecreto());
    }

    @Test
    public void porDefectoUnjugadorTieneObjetivoGeneral() {
        Jugador jugador = new Jugador();

        assertNotNull(jugador.obtenerObjetivoGeneral());
    }

    @Test
    public void porDefectoElObjetivoGeneralNoEstaCumplido() {
        Jugador jugador = new Jugador();
        assertFalse(jugador.obtenerObjetivoGeneral().estaCumplido());
    }

    @Test
    public void luegoDeQueElJugadorActivaUnaTarjetaEstaQuedaActivadaYNoSePuedeVolverAActivar() throws ElJugadorNoTieneTurnoException, ActivacionTarjetaEnRondaEquivocadaException, TarjetaNoEncontradaException, JugadorNoPoseePaisDeLaTarjetaException, LaTarjetaYaFueActivadaException {
        Jugador unJugador = new Jugador();
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(unJugador);
        Turno unTurno = new ConTurno(jugadores);

        unTurno.setRonda(new Colocacion());
        unJugador.setTurno(unTurno);

        Pais unPais = new Pais("Bolivia");
        unPais.colocarEjercito(new Ejercito(unJugador));
        Simbolo unChirimbolito = new Globo();
        Tarjeta unaTarjeta = new Tarjeta(unPais, unChirimbolito);
        unJugador.recibirTarjeta(unaTarjeta);
        unJugador.activarTarjetaPais(unPais); // no hace falta testear q se hayan sumado los ejercitos xq eso ya lo probamos y funca

        assertThrows(LaTarjetaYaFueActivadaException.class, () -> unJugador.activarTarjetaPais(unPais));
    }

    @Test
    public void luegoDeCanjearTarjetasElJugadorYaNoLasPosee() throws JugadorSinTarjetasException, SinCanjeHabilitadoException, LaTarjetaYaEstaDesactivadaException, LaTarjetaYaFueActivadaException {
        Usuario unUsuarioMock = mock(Usuario.class);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais oootroPais = new Pais("Colombia"); // jjajaja xd

        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());
        Tarjeta otraTarjeta = new Tarjeta(unPais, new Barco());
        Tarjeta oootraaTarjeta = new Tarjeta(unPais, new Canion());

        unaTarjeta.activar();
        otraTarjeta.activar();
        oootraaTarjeta.activar();

        ArrayList<Tarjeta> tarjetasElegidas = new ArrayList<>();
        tarjetasElegidas.add(unaTarjeta);
        tarjetasElegidas.add(otraTarjeta);
        tarjetasElegidas.add(oootraaTarjeta);

        when(unUsuarioMock.pedirTarjetasACanjear()).thenReturn(tarjetasElegidas);

        Jugador unJugador = new Jugador(1, unUsuarioMock);

        unJugador.recibirTarjeta(unaTarjeta);
        unJugador.recibirTarjeta(otraTarjeta);
        unJugador.recibirTarjeta(oootraaTarjeta);

        unJugador.solicitarCanje();

        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(unaTarjeta.nombrePais()));
        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(otraTarjeta.nombrePais()));
        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(oootraaTarjeta.nombrePais()));
    }


}
