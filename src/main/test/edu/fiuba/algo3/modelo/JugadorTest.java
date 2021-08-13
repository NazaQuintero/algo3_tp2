package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
import edu.fiuba.algo3.modelo.objetivos.General;
import edu.fiuba.algo3.modelo.objetivos.Objetivo;
import edu.fiuba.algo3.modelo.objetivos.Ocupacion1;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.rondas.Colocacion;
import edu.fiuba.algo3.modelo.rondas.Reagrupe;
import edu.fiuba.algo3.modelo.tarjetas.*;
import edu.fiuba.algo3.modelo.turnos.ConTurno;
import edu.fiuba.algo3.modelo.turnos.Turno;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {



    @Test
    public void seCreaUnJugadorConColorRosa() {

        Jugador jugador = new Jugador( "Cami",Color.PINK);

        assertEquals(jugador.getColor(), Color.PINK);

    }

    @Test
    public void porDefectoUnJugadorNoTienePaises() {

        Jugador jugador = new Jugador( "Cami", Color.RED);

        assertEquals(jugador.cantidadPaisesDominados(), 0);
    }


    @Test
    public void luegoDeQueElJugadorActivaUnaTarjetaEstaQuedaActivadaYNoSePuedeVolverAActivar() throws Exception{
        Jugador unJugador = new Jugador( "Cami", Color.RED);
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(unJugador);
        Turno unTurno = new ConTurno(jugadores);

        unTurno.setRonda(new Colocacion(jugadores.getPrimerJugador()));
        unJugador.setTurno(unTurno);

        Pais unPais = new Pais("Bolivia");
        unJugador.colocarEjercitos(unPais, 2);
        Simbolo unChirimbolito = new Globo();
        Tarjeta unaTarjeta = new Tarjeta(unPais, unChirimbolito);
        unJugador.recibirTarjeta(unaTarjeta);
        unJugador.activarTarjeta(unaTarjeta); // no hace falta testear q se hayan sumado los ejercitos xq eso ya lo probamos y funca

        assertThrows(LaTarjetaYaFueActivadaException.class, () -> unJugador.activarTarjeta(unaTarjeta));
    }

    @Test
    public void luegoDeCanjearTarjetasElJugadorYaNoLasPosee() throws Exception {
        Jugador jugador = new Jugador( "Martin", Color.RED);

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

        jugador.canjearTarjetas(tarjetas);
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.activarTarjeta(tArg));
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.activarTarjeta(tBra));
        assertThrows(TarjetaNoEncontradaException.class, () -> jugador.activarTarjeta(tCol));
    }

    @Test
    public void unJugadorQueNoRealizoCanjesRecibeUnPrimerCanjeAlSolicitarUnoYPuedeColocar4EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorNoTieneTodasLasTarjetasException, SinCanjeHabilitadoException {
        Jugador jugador = new Jugador( "Martin", Color.RED);

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

        jugador.canjearTarjetas(tarjetas);
        assertEquals(4, jugador.getCanjeActual().cantidadEjercitos());
    }

    @Test
    public void unJugadorQueRealizoUnCanjeRecibeUnSegundoCanjeAlSolicitarOtroYPuedeColocar7EjercitosExtra() throws LaTarjetaYaFueActivadaException, JugadorNoTieneTodasLasTarjetasException, SinCanjeHabilitadoException {
        Jugador jugador = new Jugador( "Martin", Color.RED);

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

        jugador.canjearTarjetas(tarjetas);

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

        jugador.canjearTarjetas(tarjetas);

        assertEquals(7, jugador.getCanjeActual().cantidadEjercitos());

    }

    @Test
    public void unJugadorQueSolicitaUnCanjeConDosTarjetasGloboYUnaBarcoNoLoPuedeObtener() throws LaTarjetaYaFueActivadaException {
        Jugador unJugador = new Jugador("Frank", Color.RED);

        Pais unPais1Canje = new Pais("Argentina");
        Pais otroPais1Canje = new Pais("Brasil");
        Pais ootroPais1Canje = new Pais("Colombia");

        Tarjeta unaTarjeta1Canje = new Tarjeta(unPais1Canje, new Globo());
        Tarjeta otraTarjeta1Canje = new Tarjeta(otroPais1Canje, new Globo());
        Tarjeta ootraTarjeta1Canje = new Tarjeta(ootroPais1Canje, new Barco());

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

        assertThrows(SinCanjeHabilitadoException.class, () -> unJugador.canjearTarjetas(tarjetasElegidas1Canje));

    }

    @Test
    public void terminaElTurnoSiNoPuedeAtacar(){
        Jugador jugador = new Jugador("Martin", Color.RED);
        Jugadores jugadores = new Jugadores();
        jugadores.agregarJugador(jugador);
        jugadores.agregarJugador(new Jugador("Cami", Color.LIGHTCYAN));

        ConTurno turno = new ConTurno(jugadores);
        turno.setRonda(new Reagrupe());

        jugador.setTurno(turno);

        try { jugador.atacarA(null, null, 5); }
        catch (Exception ignored) {}

        assertThrows(ElJugadorNoTieneTurnoException.class, () -> jugador.reagrupar(null, null, 5));

    }

    @Test
    public void objetivoCumplidoException(){
        Jugador jugador = new Jugador("Martin", Color.RED);
        Ocupacion1 ocup = Mockito.mock(Ocupacion1.class);
        try{ Mockito.when(ocup.estaCumplido(jugador)).thenThrow(new ContinenteInvalidoException()); }
        catch (Exception ignored) { }
        jugador.asignarObjetivo(ocup);

        assertFalse(jugador.cumpleObjetivo());
    }

    @Test
    public void jugadorPoseeNLimitrofes(){
        Pais arg = new Pais("Argentina");
        Pais bra = new Pais("Brasil");
        Pais chl = new Pais("Chile");

        arg.limitaCon(bra);
        arg.limitaCon(chl);
        bra.limitaCon(arg);
        bra.limitaCon(chl);
        chl.limitaCon(bra);
        chl.limitaCon(arg);

        Jugador jugador = new Jugador("Martin", Color.RED);
        Ejercito ej = new Ejercito(jugador);
        arg.colocarEjercito(ej);
        bra.colocarEjercito(ej);
        chl.colocarEjercito(ej);

        jugador.agregarPais(arg);
        jugador.agregarPais(bra);
        jugador.agregarPais(chl);

        assertTrue(jugador.poseeLimitrofes(1));
        assertTrue(jugador.poseeLimitrofes(2));
        assertTrue(jugador.poseeLimitrofes(3));
    }

    @Test
    public void getObjetivosDaLosObjetivos(){
        Jugador jugador = new Jugador("Martin", Color.RED);
        Ocupacion1 ocup = new Ocupacion1();
        jugador.asignarObjetivo(ocup);

        ArrayList<Objetivo> objetivos = jugador.obtenerObjetivos();
        assertTrue(objetivos.get(0) instanceof General);
        assertEquals(ocup, objetivos.get(1));
    }

    @Test
    public void getTarjetasDaLasTarjetas(){
        Jugador jugador = new Jugador("Martin", Color.RED);
        Tarjeta tarjeta = new Tarjeta(new Pais("Argentina"), new Comodin());
        Tarjeta tarjeta2 = new Tarjeta(new Pais("Brasil"), new Globo());

        jugador.recibirTarjeta(tarjeta);
        jugador.recibirTarjeta(tarjeta2);
        ArrayList<Tarjeta> tarjetas = jugador.getTarjetas();

        assertEquals(tarjeta, tarjetas.get(0));
        assertEquals(tarjeta2, tarjetas.get(1));
    }


}
