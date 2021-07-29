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

        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(unPais));
        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(otroPais));
        assertThrows(TarjetaNoEncontradaException.class, () -> unJugador.buscarTarjeta(oootroPais));
    }

    @Test
    public void unJugadorQueNoRealizoCanjesRecibeUnPrimerCanjeAlSolicitarUnoYPuedeColocar4EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        Usuario usuarioMock = mock(Usuario.class);
        Jugador unJugador = new Jugador(1, usuarioMock);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais ootroPais = new Pais("Colombia");

        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());
        Tarjeta otraTarjeta = new Tarjeta(otroPais, new Globo());
        Tarjeta ootraTarjeta = new Tarjeta(ootroPais, new Globo());

        unaTarjeta.activar();
        otraTarjeta.activar();
        ootraTarjeta.activar();

        unJugador.recibirTarjeta(unaTarjeta);
        unJugador.recibirTarjeta(otraTarjeta);
        unJugador.recibirTarjeta(ootraTarjeta);

        ArrayList<Tarjeta> tarjetasElegidas = new ArrayList<>();
        tarjetasElegidas.add(unaTarjeta);
        tarjetasElegidas.add(otraTarjeta);
        tarjetasElegidas.add(ootraTarjeta);

        when(usuarioMock.pedirTarjetasACanjear()).thenReturn(tarjetasElegidas);


        unJugador.solicitarCanje();


        assertEquals(4, unJugador.obtenerCanjeActual().cantidadEjercitos());

    }

    @Test
    public void unJugadorQueRealizoUnCanjeRecibeUnSegundoCanjeAlSolicitarOtroYPuedeColocar7EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorSinTarjetasException, LaTarjetaYaEstaDesactivadaException, SinCanjeHabilitadoException {
        Usuario usuarioMock = mock(Usuario.class);
        Jugador unJugador = new Jugador(1, usuarioMock);

        Pais unPais1Canje = new Pais("Argentina");
        Pais otroPais1Canje = new Pais("Brasil");
        Pais ootroPais1Canje = new Pais("Colombia");

        Tarjeta unaTarjeta1Canje = new Tarjeta(unPais1Canje, new Globo());
        Tarjeta otraTarjeta1Canje = new Tarjeta(otroPais1Canje, new Globo());
        Tarjeta ootraTarjeta1Canje = new Tarjeta(ootroPais1Canje, new Globo());

        unaTarjeta1Canje.activar();
        otraTarjeta1Canje.activar();
        ootraTarjeta1Canje.activar();

        unJugador.recibirTarjeta(unaTarjeta1Canje);
        unJugador.recibirTarjeta(otraTarjeta1Canje);
        unJugador.recibirTarjeta(ootraTarjeta1Canje);

        ArrayList<Tarjeta> tarjetasElegidas1Canje = new ArrayList<>();
        tarjetasElegidas1Canje.add(unaTarjeta1Canje);
        tarjetasElegidas1Canje.add(otraTarjeta1Canje);
        tarjetasElegidas1Canje.add(ootraTarjeta1Canje);

        when(usuarioMock.pedirTarjetasACanjear()).thenReturn(tarjetasElegidas1Canje);

        unJugador.solicitarCanje();

        Pais unPais2Canje = new Pais("Uruguay");
        Pais otroPais2Canje = new Pais("Bolivia");
        Pais ootroPais2Canje = new Pais("China");

        Tarjeta unaTarjeta2Canje = new Tarjeta(unPais2Canje, new Globo());
        Tarjeta otraTarjeta2Canje = new Tarjeta(otroPais2Canje, new Barco());
        Tarjeta ootraTarjeta2Canje = new Tarjeta(ootroPais2Canje, new Canion());

        unaTarjeta2Canje.activar();
        otraTarjeta2Canje.activar();
        ootraTarjeta2Canje.activar();

        unJugador.recibirTarjeta(unaTarjeta1Canje);
        unJugador.recibirTarjeta(otraTarjeta1Canje);
        unJugador.recibirTarjeta(ootraTarjeta1Canje);

        ArrayList<Tarjeta> tarjetasElegidas2Canje = new ArrayList<>();
        tarjetasElegidas2Canje.add(unaTarjeta2Canje);
        tarjetasElegidas2Canje.add(otraTarjeta2Canje);
        tarjetasElegidas2Canje.add(ootraTarjeta2Canje);

        when(usuarioMock.pedirTarjetasACanjear()).thenReturn(tarjetasElegidas2Canje);


        unJugador.solicitarCanje();


        assertEquals(7, unJugador.obtenerCanjeActual().cantidadEjercitos());

    }


}
