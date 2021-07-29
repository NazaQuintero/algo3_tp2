package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.batallasDeDados.ArbitroDeDado;
import edu.fiuba.algo3.modelo.batallasDeDados.ProcesadorResultado;
import edu.fiuba.algo3.modelo.batallasDeDados.Resultado;
import edu.fiuba.algo3.modelo.batallasDeDados.ResultadoBatalla;
import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.EjercitosInsuficientesException;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.ElPaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;

import java.util.*;


public class Tablero {
    private HashMap<String,Pais> paises;

    public Tablero() {
        paises = new HashMap<>();
    }

    public void agregarPais(Pais pais){ paises.put(pais.nombre, pais); }

    public void repartirPaises(ArrayList<Jugador> jugadores) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException {
        ArrayList<Pais> paises = new ArrayList<>(this.paises.values());
        Collections.shuffle(paises);

        int cant_jugadores = jugadores.size();
        for (int i = 0; i < paises.size(); i++) jugadores.get(i % cant_jugadores).colocarEjercitos(paises.get(i), 1);
    }

    public Pais obtenerPais(String nombrePais) {
        return paises.get(nombrePais);
    }

    public void ataque(String paisAtacante, String paisDefensor, int cantidadEjercitos) throws ElPaisNoEsLimitrofeException, EjercitosInsuficientesException {
        Resultado resultado = obtenerPais(paisAtacante).atacarA(obtenerPais(paisDefensor), cantidadEjercitos);
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
}