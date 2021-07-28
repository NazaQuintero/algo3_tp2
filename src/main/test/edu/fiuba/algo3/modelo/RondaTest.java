package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.*;
import edu.fiuba.algo3.modelo.continentes.Asia;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.fichas.Ejercito;
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
        assertThrows(ElJugadorNoTieneTurnoException.class, () -> jugador2.atacarA(otroPais, unPais));

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

        assertThrows(NoEsRondaDeAtaqueException.class, () -> jugador1.atacarA(unPais, otroPais));

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

        assertThrows(NoEsRondaDeReagrupeException.class, () -> jugador1.reagrupar(unPais, otroPais));

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

        assertThrows(NoEsRondaDeColocacionException.class, () -> jugador1.colocarEjercitos(unPais));

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
    public void seActivaUnaTarjetaDePaisEnRondaDeColocacionSumandole2EjercitosADichoPais() throws JugadorNoPoseePaisDeLaTarjetaException, TarjetaNoEncontradaException, ActivacionTarjetaEnRondaEquivocadaException, ElJugadorNoTieneTurnoException, LaTarjetaYaFueActivadaException {
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

        jugador1.activarTarjetaPais(unPais); // al activar la tarjeta se suman 2 ejercitos

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

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> jugador1.activarTarjetaPais(unPais));

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

        assertThrows(ActivacionTarjetaEnRondaEquivocadaException.class, () -> jugador1.activarTarjetaPais(unPais));

    }

    @Test
    public void seJuegaUnaRondaCon3JugadoresJugador2DominaAsiaNadieAtacaPeroSiColocan() throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException {
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
        Pais china = new Pais("China");

        unPais.colocarEjercito(new Ejercito(jugador1));
        otroPais.colocarEjercito(new Ejercito(jugador3));
        china.colocarEjercito(new Ejercito(jugador2)); // cada pais tiene un ejercito de cada jugador

        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // elegimos al jugador1 como primer jugador
        unTurno.setRonda(new Colocacion());
        
        Asia asia = new Asia("Asia");
        asia.agregarPais(china);

        assertEquals(jugador1, unTurno.obtenerJugadorTurnoActual());

        jugador1.colocarEjercitos(unPais);
        jugador1.finalizarRonda();

        assertEquals(jugador2, unTurno.obtenerJugadorTurnoActual());

        jugador2.colocarEjercitos(china);
        jugador2.finalizarRonda();

        assertEquals(jugador3, unTurno.obtenerJugadorTurnoActual());

        jugador3.colocarEjercitos(otroPais);
        jugador3.finalizarRonda();

        assertEquals(3, unPais.cantidadEjercitos());
        assertEquals(jugador1, unPais.dominadoPor());

        // Asia dominado por Jugador2
        assertEquals(3, china.cantidadEjercitos());
        assertEquals(jugador2, china.dominadoPor());
        assertTrue(asia.dominadoPor(jugador2));

        assertEquals(3, otroPais.cantidadEjercitos());
        assertEquals(jugador3, otroPais.dominadoPor());

    }


    @Test
    public void rondaDeAtaqueEntre2JugadoresJugador1AtacaYConquista2PaisesDelJugador2() throws ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        Jugadores jugadores = new Jugadores();
        Usuario usuarioMock = mock(Usuario.class);

        Jugador jugador1 = new Jugador(1, usuarioMock);
        Jugador jugador2 = new Jugador(2, usuarioMock);

        jugadores.agregarJugador(jugador1);
        jugadores.agregarJugador(jugador2);

        Pais paisAtacante = new Pais("Argentina");
        Pais paisDefensor = new Pais("Brasil");
        Pais paisDefensor2 = new Pais("Bolivia");

        Dado dadoAtacante = new DadoPersonalizado(6);
        Dado dadoDefensor = new DadoPersonalizado(1);
        Dado dadoDefensor2 = new DadoPersonalizado(1);

        Dados dadosAtacante = new Dados();
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);
        dadosAtacante.agregarDado(dadoAtacante);

        Dados dadosDefensor = new Dados();

        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);
        dadosDefensor.agregarDado(dadoDefensor);

        Dados dadosDefensor2 = new Dados();

        dadosDefensor2.agregarDado(dadoDefensor2);
        dadosDefensor2.agregarDado(dadoDefensor2);
        dadosDefensor2.agregarDado(dadoDefensor2);

        Ejercito ejercitoAtacante = new Ejercito(jugador1);
        ejercitoAtacante.modificarCantidad(1);

        Ejercito ejercitoDefensor = new Ejercito(jugador2);
        Ejercito ejercitoDefensor2 = new Ejercito(jugador2);

        paisAtacante.colocarEjercito(ejercitoAtacante);
        paisAtacante.modificarCantidadEjercito(4);
        paisDefensor.colocarEjercito(ejercitoDefensor);
        paisDefensor2.colocarEjercito(ejercitoDefensor2);

        ejercitoAtacante.setDados(dadosAtacante);
        ejercitoDefensor.setDados(dadosDefensor);
        ejercitoDefensor2.setDados(dadosDefensor2);

        // Turno
        Turno unTurno = new ConTurno(jugadores);
        unTurno.seleccionarPrimerJugador(0); // le toca a jugador1
        assertEquals("Ataque", unTurno.obtenerRondaActual().obtenerDescripcion());


        //Ataque al primer pais defensor
        ResultadoBatalla resultadoBatalla = jugador1.atacarA(paisAtacante, paisDefensor);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);

        assertEquals(jugador1, paisDefensor.dominadoPor());

        //Ataque al segundo pais defensor
        resultadoBatalla = jugador1.atacarA(paisAtacante, paisDefensor2);
        ProcesadorResultado.obtenerInstancia().procesar(resultadoBatalla);
        assertEquals(jugador1, paisDefensor2.dominadoPor());

    }

}


