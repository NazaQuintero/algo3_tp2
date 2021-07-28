package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.continentes.Continente;
import edu.fiuba.algo3.modelo.excepciones.ElJugadorNoTieneTurnoException;
import edu.fiuba.algo3.modelo.excepciones.NoEsRondaDeColocacionException;

import java.util.*;


public class Tablero {
    private HashMap<String,Pais> paises;

    public Tablero() {
        paises = new HashMap<>();
        continentes = new HashMap<>();
    }

    public void agregarPais(Pais pais){ paises.put(pais.nombre, pais); }

    public void repartirPaises(ArrayList<Jugador> jugadores) throws NoEsRondaDeColocacionException, ElJugadorNoTieneTurnoException {
        ArrayList<Pais> paises = new ArrayList<>(this.paises.values());
        Collections.shuffle(paises);

        int cant_jugadores = jugadores.size();
        for (int i = 0; i < paises.size(); i++) jugadores.get(i % cant_jugadores).colocarEjercitos(paises.get(i));
    }

    public Pais obtenerPais(String nombrePais) {
        return paises.get(nombrePais);
    }

}