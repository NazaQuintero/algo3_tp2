package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RondaTest {
    @Test
    public void unJugadorNoPuedeAtacarSiNoTieneTurno() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Usuario usuario3 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        Jugador jugador3 = new Jugador(3, usuario3);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");
        Pais oootroPais = new Pais("Bolivia");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));
        oootroPais.colocarEjercito(new Ejercito(jugador3)); // se coloca 1 solo ejercito en cada pais

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertNotEquals(jugador2, turno.obtenerJugadorTurnoActual());
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> { jugador2.atacarA(otroPais, unPais); });

    }

    @Test
    public void elJugadorConTurnoNoPuedeAtacarSiLaRondaNoEsDeAtaque() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        jugador1.finalizarRonda(); // aca finaliza la ronda de ataque

        Ronda unaRonda = turno.obtenerRondaActual();
        assertNotEquals("Ataque", unaRonda.obtenerDescripcion());
        assertEquals("Reagrupe", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeAtaqueException.class, () -> { jugador1.atacarA(unPais, otroPais); });

    }


    @Test
    public void elJugadorConTurnoNoPuedeReagruparSiLaRondaNoEsDeReagrupe() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        Ronda unaRonda = turno.obtenerRondaActual();

        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertNotEquals("Reagrupe", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeReagrupeException.class, () -> { jugador1.reagrupar(unPais, otroPais); });

    }

    @Test
    public void elJugadorConTurnoNoPuedeColocarEjercitosSiLaRondaNoEsDeColocacion() {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno turno = new ConTurno(jugadores);
        turno.seleccionarPrimerJugador(0); // es el jugador 1

        Ronda unaRonda = turno.obtenerRondaActual();

        assertEquals(jugador1, turno.obtenerJugadorTurnoActual());
        assertEquals("Ataque", unaRonda.obtenerDescripcion());
        assertNotEquals("Colocacion", unaRonda.obtenerDescripcion());

        assertThrows(NoEsRondaDeColocacionException.class, () -> { jugador1.colocarEjercitos(unPais); });

    }


    @Test
    public void rondaConDosJugadoresNoSeAtacanYSeColocan2NuevosEjercitosCadaUno() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        Usuario usuarioMock1 = mock(Usuario.class);
        Usuario usuarioMock2 = mock(Usuario.class);
        when(usuarioMock1.pedirCantidad()).thenReturn(2);
        when(usuarioMock2.pedirCantidad()).thenReturn(2);
        Jugadores jugadores = new Jugadores();
        Jugador jugador1 = new Jugador(1, usuarioMock1); // ahora cada jugador recibe un objeto usuario, si fuera online tendria mas comportamiento asi q esta bueno (?
        Jugador jugador2 = new Jugador(2, usuarioMock2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chile");


        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador2)); // esto seria previo al comienzo de la partida obviamente

        // a este punto cada pais tiene 1 ejercito de cada jugador

        assertEquals(1, unPais.cantidadEjercitos());
        assertEquals(jugador1, unPais.dominadoPor());
        assertEquals(1, otroPais.cantidadEjercitos());
        assertEquals(jugador2, otroPais.dominadoPor());

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // seleccionamos a jugador1 como primer jugador

        jugador1.finalizarRonda(); // no ataca
        jugador1.finalizarRonda(); // no reagrupa
        jugador2.finalizarRonda(); // no ataca
        jugador2.finalizarRonda(); // no reagrupa

        // le toca nuevamente al jugador1, ronda de colocacion
        assertEquals("Colocacion", unTurno.obtenerRondaActual().obtenerDescripcion());
        assertEquals(jugador1, unTurno.obtenerJugadorTurnoActual());

        jugador1.colocarEjercitos(unPais); // coloca 2 ejercitos
        jugador1.finalizarRonda();

        jugador2.colocarEjercitos(otroPais); // coloca 2 ejercitos
        jugador2.finalizarRonda();

        assertEquals(3, unPais.cantidadEjercitos());
        assertEquals(3, otroPais.cantidadEjercitos());
    }

    @Test
    public void seActivaUnaTarjetaDePaisEnRondaDeColocacionSumandole2EjercitosADichoPais() throws JugadorNoPoseePaisDeLaTarjetaException, TarjetaNoEncontradaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1)); // por defecto cada pais tiene 1 ejercito
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0);

        unTurno.setRonda(new Colocacion());

        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());

        jugador1.recibirTarjeta(unaTarjeta);

        jugador1.activarTarjetaPais("Argentina"); // al activar la tarjeta se suman 2 ejercitos

        assertEquals(jugador1, unPais.dominadoPor());
        assertEquals(3, unPais.cantidadEjercitos());

    }

    @Test
    public void noSePuedeActivarUnaTarjetaDePaisEnUnaRondaDeAtaque() {

        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1)); // por defecto cada pais tiene 1 ejercito
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // por defecto la ronda es de ataque

        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());

        jugador1.recibirTarjeta(unaTarjeta);

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> { jugador1.activarTarjetaPais("Argentina"); });

    }

    @Test
    public void noSePuedeActivarUnaTarjetaDePaisEnUnaRondaDeReagrupe() {

        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Jugador jugador1 = new Jugador(1, usuario1);
        Jugador jugador2 = new Jugador(2, usuario2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Brasil");

        unPais.colocarEjercito(new Ejercito(jugador1)); // por defecto cada pais tiene 1 ejercito
        otroPais.colocarEjercito(new Ejercito(jugador1));

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0);

        unTurno.setRonda(new Reagrupe());

        Tarjeta unaTarjeta = new Tarjeta(unPais, new Globo());

        jugador1.recibirTarjeta(unaTarjeta);

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> { jugador1.activarTarjetaPais("Argentina"); });

    }

    @Test
    public void seJuegaUnaRondaCon3JugadoresJugador2DominaAsiaNadieAtacaPeroSiColocan() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
        MockitoAnnotations.initMocks(this);
        Jugadores jugadores = new Jugadores();
        Usuario usuario1Mock = mock(Usuario.class);
        Usuario usuario2Mock = mock(Usuario.class);
        Usuario usuario3Mock = mock(Usuario.class);
        Jugador jugador1 = new Jugador(1, usuario1Mock);
        Jugador jugador2 = new Jugador(2, usuario2Mock);
        Jugador jugador3 = new Jugador(3, usuario3Mock);
        when(usuario1Mock.pedirCantidad()).thenReturn(2);
        when(usuario2Mock.pedirCantidad()).thenReturn(2);
        when(usuario3Mock.pedirCantidad()).thenReturn(2);
        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);
        jugadores.agregarJugador(jugador3);

        Pais unPais = new Pais("Argentina");
        Pais otroPais = new Pais("Chile");
        Pais oootroPais = new Pais("China");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador3));
        oootroPais.colocarEjercito(new Ejercito(jugador2)); // cada pais tiene un ejercito de cada jugador

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // elegimos al jugador1 como primer jugador
        unTurno.setRonda(new Colocacion());
        
        Asia asia = mock(Asia.class);

        when(asia.dominadoPor(jugador2)).thenReturn(true);
        when(asia.obtenerCantidadEjercito()).thenReturn(7);

        assertEquals(jugador1, unTurno.obtenerJugadorTurnoActual());

        jugador1.colocarEjercitos(unPais);
        jugador1.finalizarRonda();

        assertEquals(jugador2, unTurno.obtenerJugadorTurnoActual());

        jugador2.colocarEjercitos(oootroPais);
        jugador2.finalizarRonda();

        assertEquals(jugador3, unTurno.obtenerJugadorTurnoActual());

        jugador3.colocarEjercitos(otroPais);
        jugador3.finalizarRonda();

        assertEquals(3, unPais.cantidadEjercitos());
        assertEquals(jugador1, unPais.dominadoPor());

        assertEquals(3, oootroPais.cantidadEjercitos());
        assertEquals(jugador2, oootroPais.dominadoPor());

        assertEquals(3, otroPais.cantidadEjercitos());
        assertEquals(jugador3, otroPais.dominadoPor());

    }

    @Test
    public void rondaDeAtaqueEntre2JugadoresJugador1AtacaYConquista2PaisesDelJugador2() throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        Jugadores jugadores = new Jugadores();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();

        Jugador jugador1Spy = spy(new Jugador(1, usuario1));
        Jugador jugador2Spy = spy(new Jugador(2, usuario2));

        jugadores.agregarJugador(jugador1Spy);
        jugadores.agregarJugador(jugador2Spy);


        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        Pais paisDefensor2 = new Pais("Bolivia");


        Dado dadoAtacanteMock = mock(Dado.class);
        when(dadoAtacanteMock.obtenerValor()).thenReturn(1);

        Dado dadoDefensorMock = mock(Dado.class);
        when(dadoDefensorMock.obtenerValor()).thenReturn(1);


        when(dadoAtacanteMock.compareTo(dadoDefensorMock)).thenReturn(-1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);
        dadosAtacante.agregarDado(dadoAtacanteMock);

        Dados dadosDefensor = new Dados();

        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);
        dadosDefensor.agregarDado(dadoDefensorMock);

        Mockito.when(jugador1Spy.tirarDados(paisAtacante)).thenReturn(dadosAtacante);
        when(jugador2Spy.tirarDados(paisDefensor)).thenReturn(dadosDefensor);
        when(jugador2Spy.tirarDados(paisDefensor2)).thenReturn(dadosDefensor);

        Ejercito ejercitoAtacante = new Ejercito(jugador1Spy);
        ejercitoAtacante.modificarCantidad(1);
        Ejercito ejercitoDefensor = new Ejercito(jugador2Spy);
        Ejercito ejercitoDefensor2 = new Ejercito(jugador2Spy);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisAtacante.modificarCantidadEjercito(4);
        paisDefensor.colocarEjercito(ejercitoDefensor);
        paisDefensor2.colocarEjercito(ejercitoDefensor2);

        paisAtacante.atacarA(paisDefensor);

        assertEquals(1, paisDefensor.cantidadEjercitos());
        assertEquals(jugador1Spy, paisDefensor.dominadoPor());


        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // le toca a jugador1

        assertEquals("Ataque", unTurno.obtenerRondaActual().obtenerDescripcion());

        jugador1Spy.atacarA(paisAtacante, paisDefensor);

        assertEquals(jugador1Spy, paisDefensor.dominadoPor());

        jugador1Spy.atacarA(paisAtacante, paisDefensor2);

        assertEquals(jugador1Spy, paisDefensor2.dominadoPor());

    }

}


