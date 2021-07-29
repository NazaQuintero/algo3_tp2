package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.batallasDeDados.ArbitroDeDado;
import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.*;

import java.util.*;


public class Tablero {
    private final HashMap<String,Pais> paises;

    public Tablero() {
        paises = new HashMap<>();
    }

    public void agregarPais(Pais pais){ paises.put(pais.nombre, pais); }

    public void repartirPaises(Jugadores jugadores){
        ArrayList<Pais> paises = new ArrayList<>(this.paises.values());
        Collections.shuffle(paises);
        int cant_jugadores = jugadores.obtenerCantidad();

        // No se levanta ninguna excepcion en esta fase
        try{
            for (int i = 0; i < paises.size(); i++){
                Jugador jugador = jugadores.obtenerJugador(i % cant_jugadores);
                jugador.colocarEjercitos(paises.get(i), 1);
            }
        }
        catch (ElJugadorNoTieneTurnoException | NoEsRondaDeColocacionException | JugadorNoExisteException ignored){}
    }

    public Pais obtenerPais(String nombrePais) {
        return paises.get(nombrePais);
    }

    public void ataque(Jugador jugador, String paisAtacante, String paisDefensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException, ElJugadorNoTieneTurnoException, NoEsRondaDeAtaqueException {
        Resultado resultado = jugador.atacarA(obtenerPais(paisAtacante), obtenerPais(paisDefensor), cantidadEjercitos);
        ProcesadorResultado.obtenerInstancia().procesar(resultado);
    }

    public void colocarEjercitos(Jugador jugador, String nombrePais, int cantidadEjercitos) throws ElJugadorNoTieneTurnoException, NoEsRondaDeColocacionException{
        jugador.colocarEjercitos(obtenerPais(nombrePais), cantidadEjercitos);
    }

    public int cantidadEjercitosEn(String nombrePais) {
        return obtenerPais(nombrePais).cantidadEjercitos();
    }

    public Jugador paisDominadoPor(String nombrePais) {
        return obtenerPais(nombrePais).dominadoPor();
    }

    public void reagrupar(Jugador jugador, String nombrePaisOrigen, String nombrePaisDestino, int cantidadAMover) throws ElPaisNoEsLimitrofeException, NoEsRondaDeReagrupeException, ElJugadorNoTieneTurnoException {
        jugador.reagrupar(obtenerPais(nombrePaisOrigen), obtenerPais(nombrePaisDestino), cantidadAMover);
    }
}