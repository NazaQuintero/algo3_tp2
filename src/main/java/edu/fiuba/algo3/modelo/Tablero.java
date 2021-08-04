package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.paises.MultitonPaises;
import edu.fiuba.algo3.modelo.paises.Pais;
import edu.fiuba.algo3.modelo.tarjetas.Tarjeta;

import java.util.*;


public class Tablero {

    public void repartirPaises(Jugadores jugadores) {
        ArrayList<Pais> paises = new ArrayList<>(MultitonPaises.obtenerTodosLosPaises());
        Collections.shuffle(paises);
        int cantidadJugadores = jugadores.obtenerCantidad();

        // No se levanta ninguna excepcion en esta fase
        try {
            for (int i = 0; i < paises.size(); i++) {
                Jugador jugador = jugadores.obtenerJugador(i % cantidadJugadores);
                jugador.colocarEjercitos(paises.get(i), 1);
            }
        } catch (ElJugadorNoTieneTurnoException | NoEsRondaDeColocacionException | JugadorNoExisteException | PaisOcupadoPorOtroJugadorException ignored) {
        }
    }

    public void ataque(Jugador jugador, Pais pAtacante, Pais pDefensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException, ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        Resultado resultado = jugador.atacarA(pAtacante, pDefensor, cantidadEjercitos);
        ProcesadorResultado.obtenerInstancia().procesar(resultado);
    }

    public void colocarEjercitos(Jugador jugador, Pais pais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException, PaisOcupadoPorOtroJugadorException {
        jugador.colocarEjercitos(pais, cantidadEjercitos);
    }

    public int cantidadEjercitosEn(Pais pais) {
        return pais.cantidadEjercitos();
    }

    public Jugador paisDominadoPor(Pais pais) {
        return pais.dominadoPor();
    }

    public void reagrupar(Jugador jugador, Pais paisOrigen, Pais paisDestino, int cantidadAMover) throws ElPaisNoEsLimitrofeException, NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException {
        jugador.reagrupar(paisOrigen, paisDestino, cantidadAMover);
    }

    public int cantidadPaisesDominados(Jugador jugador) {
        return jugador.cantidadPaisesDominados();
    }

}